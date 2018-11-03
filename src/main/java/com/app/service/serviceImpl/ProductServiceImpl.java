package com.app.service.serviceImpl;

import com.app.model.*;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.utils.DateConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> buyProduct(OrderEntity orderEntity) {
        String titleOfProduct = orderEntity.getTitle();
        Integer count = orderEntity.getCount();
        List<ProductEntity> orderedProduct = productRepository.readAllByTitle(titleOfProduct);
//        if (orderedProduct.isEmpty()){
//            return orderedProduct;
//        }
        return orderedProduct;
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        ProductEntity productEntity = convertToProductEntity(productDto);
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
    public ProductEntity getProductById(Long id) {
        return productRepository.readById(id);
    }

    @Override
    public List<ProductEntity> getAllProductsByTitle(String title) {
        return productRepository.readAllByTitle(title);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    //TODO description not use
    @Override
    public int block(List<Long> ids, String description) {
        return productRepository.blockProductsWithIds(ids, description);

    }

    private ProductEntity convertToProductEntity(ProductDto productDto) {
        String sku = productDto.getUuid();
        String title = productDto.getTitle();
        Double price = productDto.getPrice();
        ProductDescriptionEntity productDescriptionEntity = getProductDescriptionFromDto(productDto);
        ShipmentEntity shipmentEntity = getShipmentFromDto(productDto);
        return new ProductEntity(sku, title, price, false, false, productDescriptionEntity, shipmentEntity);
    }

    private ShipmentEntity getShipmentFromDto(ProductDto productDto) {
        ShipmentDto shipmentDto = productDto.getShipment();
        Long shipmentId = shipmentDto.getId();
        String shipmentDescription = shipmentDto.getDescription();
        String shipmentIncomeDate = shipmentDto.getIncomeDate();
        Timestamp shipmentDate = DateConverterUtil.convertStringDateToTimestamp(shipmentIncomeDate);
        ShipmentEntity shipmentEntity = new ShipmentEntity(shipmentDescription, shipmentDate);
        shipmentEntity.setId(shipmentId);
        return shipmentEntity;
    }

    private ProductDescriptionEntity getProductDescriptionFromDto(ProductDto productDto) {
        ProductDescriptionDto productDescriptionDto = productDto.getProductDescription();
        Long productDescriptionId = productDescriptionDto.getId();
        String nameDescription = productDescriptionDto.getName();
        String description = productDescriptionDto.getDescription();
        ProductDescriptionEntity productDescriptionEntity = new ProductDescriptionEntity(nameDescription, description);
        productDescriptionEntity.setId(productDescriptionId);
        return productDescriptionEntity;
    }

    private void changeFieldsProductDtoToProductEntity(ProductDto productDto, ProductEntity productEntityFromDb) {
        String sku = productDto.getUuid();
        if (sku != null) {
            productEntityFromDb.setUuid(sku);
        }
        String title = productDto.getTitle();
        if (title != null) {
            productEntityFromDb.setTitle(title);
        }
        Double price = productDto.getPrice();
        if (price != null) {
            productEntityFromDb.setPrice(price);
        }
        ProductDescriptionDto productDescriptionDto = productDto.getProductDescription();
        if (productDescriptionDto != null) {
            ProductDescriptionEntity productDescriptionEntity = productEntityFromDb.getProductDescriptionEntity();
            String name = productDescriptionDto.getName();
            if (name != null) {
                productDescriptionEntity.setName(name);
            }
            String description = productDescriptionDto.getDescription();
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
