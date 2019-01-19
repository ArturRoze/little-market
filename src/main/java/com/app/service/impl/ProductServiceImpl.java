package com.app.service.impl;

import com.app.domain.ConverterToEntity;
import com.app.domain.income.*;
import com.app.domain.outcome.ProductMsgResponse;
import com.app.exception.ProductException;
import com.app.model.ProductDescriptionEntity;
import com.app.model.ProductEntity;
import com.app.model.ShipmentEntity;
import com.app.model.SubCategoryEntity;
import com.app.repository.ProductRepository;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.utils.DateConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConverterToEntity converterToEntity;
    private final OrderService orderService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ConverterToEntity converterToEntity, OrderService orderService) {
        this.productRepository = productRepository;
        this.converterToEntity = converterToEntity;
        this.orderService = orderService;
    }

    @Override
    public void buyProduct(OrderDto orderDto) {
        List<ProductEntity> productsByOrderProductsFromDb = getProductsFromOrderProducts(orderDto);
        if (productsByOrderProductsFromDb.isEmpty()) {
            List<ProductMsgResponse> productsMsgResponse = getProductsMsgResponse(productsByOrderProductsFromDb);
            throw new ProductException("products no found", productsMsgResponse);
        }
        if (!compareUuids(orderDto.getProducts(), productsByOrderProductsFromDb)) {
            List<String> unavailableProducts = getUnavailableProducts(orderDto, productsByOrderProductsFromDb);
            List<ProductMsgResponse> productsMsgResponse = getProductsMsgResponseByUuids(unavailableProducts);
            throw new ProductException("These products are no longer available", productsMsgResponse);
        }
        List<ProductEntity> soldProductEntities = checkSoldProducts(productsByOrderProductsFromDb);
        if (soldProductEntities != null && !soldProductEntities.isEmpty()) {
            List<ProductMsgResponse> productsMsgResponse = getProductsMsgResponse(soldProductEntities);
            throw new ProductException("These products are sold", productsMsgResponse);
        }
        List<ProductEntity> disabledProductEntities = checkDisabledProducts(productsByOrderProductsFromDb);
        if (disabledProductEntities != null && !disabledProductEntities.isEmpty()) {
            List<ProductMsgResponse> productsMsgResponse = getProductsMsgResponse(disabledProductEntities);
            throw new ProductException("These products are disabled", productsMsgResponse);
        }
        markSoldProductsInDb(productsByOrderProductsFromDb);
        orderService.addOrder(orderDto);
    }

    private List<ProductMsgResponse> getProductsMsgResponse(List<ProductEntity> products) {
        List<String> productTitles = products.stream()
                .map(item -> item.getTitle())
                .collect(Collectors.toList());
        List<ProductMsgResponse> productMsgResponses = new ArrayList<>();
        for (String item : productTitles) {
            productMsgResponses.add(new ProductMsgResponse(item));
        }
        return productMsgResponses;
    }

    private List<ProductMsgResponse> getProductsMsgResponseByUuids(List<String> productUUids) {
        List<ProductMsgResponse> productMsgResponses = new ArrayList<>();
        for (String uuid : productUUids) {
            productMsgResponses.add(new ProductMsgResponse(uuid));
        }
        return productMsgResponses;
    }

    private List<ProductEntity> checkSoldProducts(List<ProductEntity> productsByOrderProductsFromDb) {
        return productsByOrderProductsFromDb.stream()
                .filter(item -> item.isSold())
                .collect(Collectors.toList());
    }

    private List<ProductEntity> checkDisabledProducts(List<ProductEntity> productsByOrderProductsFromDb) {
        return productsByOrderProductsFromDb.stream()
                .filter(item -> item.isDisabled())
                .collect(Collectors.toList());
    }

    private List<ProductEntity> getProductsFromOrderProducts(OrderDto orderDto) {
        List<String> uuidsFromOrder = orderDto.getProducts().stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        return productRepository.readProductsByUuids(uuidsFromOrder);
    }

    private boolean compareUuids(List<UserProductDto> userProducts, List<ProductEntity> products) {
        List<String> uuidsFromOrder = userProducts.stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        List<String> uuidsFromDb = products.stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        return uuidsFromOrder.size() == uuidsFromDb.size();
    }

    private List<String> getUnavailableProducts(OrderDto orderDto, List<ProductEntity> productsFromDb) {
        List<String> uuidsFromOrder = orderDto.getProducts().stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        List<String> uuidsFromDb = productsFromDb.stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        List<String> unavailableUuids = new ArrayList<>();
        for (String anUuidsFromOrder : uuidsFromOrder) {
            if (!uuidsFromDb.contains(anUuidsFromOrder)) {
                unavailableUuids.add(anUuidsFromOrder);
            }
        }
        return unavailableUuids;
    }

    private void markSoldProductsInDb(List<ProductEntity> productsByOrderProductsFromDb) {
        List<String> collect = productsByOrderProductsFromDb.stream()
                .map(item -> item.getUuid())
                .collect(Collectors.toList());
        productRepository.sellProducts(collect);
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        ProductEntity productEntity = converterToEntity.convertToProductEntity(productDto);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return savedProductEntity.getId() != null;
    }

    @Override
    public ProductEntity updateProduct(ProductDto productDto, Long id) {
        ProductEntity productEntityFromDb = productRepository.readById(id);
        changeFieldsProductDtoToProductEntity(productDto, productEntityFromDb);
        return productRepository.update(productEntityFromDb);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.readAll();
    }

    @Override
    public List<ProductEntity> getAvailableProducts() {
        List<ProductEntity> productEntities = productRepository.readAll();
        return productEntities.stream()
                .filter(item -> !item.isDisabled() && !item.isSold())
                .collect(Collectors.toList());
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.readById(id);
    }

    @Override
    public List<ProductEntity> getAllProductsByTitle(String title) {
        return productRepository.readAllByTitle(title);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public int block(List<Long> ids, String description) {
        return productRepository.blockProductsWithIds(ids, description);
    }

    private void changeFieldsProductDtoToProductEntity(ProductDto productDto, ProductEntity productEntityFromDb) {
        String uuid = productDto.getUuid();
        if (uuid != null) {
            productEntityFromDb.setUuid(uuid);
        }
        SubCategoryDto subCategoryDto = productDto.getSubCategory();
        if (subCategoryDto != null) {
            SubCategoryEntity subCategoryEntity = productEntityFromDb.getSubCategoryEntity();
            String nameSubCategoryDto = subCategoryDto.getName();
            if (nameSubCategoryDto != null) {
                subCategoryEntity.setName(nameSubCategoryDto);
            }
            CategoryDto categoryDto = subCategoryDto.getCategory();
            if (categoryDto != null) {
                String nameCategoryDto = categoryDto.getName();
                if (nameCategoryDto != null) {
                    subCategoryEntity.getCategoryEntity().setName(nameCategoryDto);
                }
            }
        }
        String title = productDto.getTitle();
        if (title != null) {
            productEntityFromDb.setTitle(title);
        }
        Double price = productDto.getPrice();
        if (price != null) {
            productEntityFromDb.setPrice(price);
        }
        boolean disabled = productDto.isDisabled();
        if (productEntityFromDb.isDisabled() != disabled) {
            productEntityFromDb.setDisabled(disabled);
        }
        String disabledReason = productDto.getDisabledReason();
        if (disabledReason != null) {
            productEntityFromDb.setDisabledReason(disabledReason);
        }
        DescriptionDto descriptionDto = productDto.getDescription();
        if (descriptionDto != null) {
            ProductDescriptionEntity productDescriptionEntity = productEntityFromDb.getProductDescriptionEntity();
            String name = descriptionDto.getName();
            if (name != null) {
                productDescriptionEntity.setName(name);
            }
            String description = descriptionDto.getDescription();
            if (description != null) {
                productDescriptionEntity.setDescription(description);
            }
        }
        ShipmentDto shipmentDto = productDto.getShipment();
        if (shipmentDto != null) {
            ShipmentEntity shipmentEntity = productEntityFromDb.getShipmentEntity();
            String description = shipmentDto.getDescription();
            if (description != null) {
                shipmentEntity.setDescription(description);
            }
            String incomeDate = shipmentDto.getIncomeDate();
            Timestamp timestampDate = DateConverterUtil.convertStringDateToTimestamp(incomeDate);
            shipmentEntity.setIncomeDate(timestampDate);
        }
    }
}
