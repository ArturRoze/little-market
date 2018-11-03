package com.app.service.serviceImpl;

import com.app.model.*;
import com.app.repository.ProductRepository;
import com.app.service.CategoryService;
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
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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

    @Override
    public int block(List<Long> ids, String description) {
        return productRepository.blockProductsWithIds(ids, description);

    }

    private ProductEntity convertToProductEntity(ProductDto productDto) {
        SubCategoryDto subCategoryDto = productDto.getSubCategory();
        String name = subCategoryDto.getName();
        SubCategoryEntity subCategoryEntity = categoryService.getSubCategoryName(name);
        String title = productDto.getTitle();
        Double price = productDto.getPrice();
        String disabledReason = productDto.getDisabledReason();
        ProductDescriptionEntity productDescriptionEntity = getProductDescriptionFromDto(productDto);
        ShipmentEntity shipmentEntity = getShipmentFromDto(productDto);
        return new ProductEntity(subCategoryEntity, title, price, false, disabledReason,false, productDescriptionEntity, shipmentEntity);
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
        String uuid = productDto.getUuid();
        if (uuid != null) {
            productEntityFromDb.setUuid(uuid);
        }
        SubCategoryDto subCategoryDto = productDto.getSubCategory();
        if (subCategoryDto != null){
            SubCategoryEntity subCategoryEntity = productEntityFromDb.getSubCategoryEntity();
            String nameSubCategoryDto = subCategoryDto.getName();
            if (nameSubCategoryDto != null){
                subCategoryEntity.setName(nameSubCategoryDto);
            }
            CategoryDto categoryDto = subCategoryDto.getCategory();
            if (categoryDto != null){
                String nameCategoryDto = categoryDto.getName();
                if (nameCategoryDto != null){
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
        boolean disabled = productDto.getDisabled();
        if (productEntityFromDb.isDisabled() != disabled){
            productEntityFromDb.setDisabled(disabled);
        }
        String disabledReason = productDto.getDisabledReason();
        if (disabledReason != null){
            productEntityFromDb.setDisabledReason(disabledReason);
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
