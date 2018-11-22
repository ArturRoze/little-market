package com.app.domain;

import com.app.model.*;
import com.app.repository.ProductRepository;
import com.app.repository.SubCategoryRepository;
import com.app.repository.UserRepository;
import com.app.service.impl.OrderServiceImpl;
import com.app.utils.DateConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterToEntity {

    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public ConverterToEntity(SubCategoryRepository subCategoryRepository, ProductRepository productRepository, UserRepository userRepository, OrderServiceImpl orderServiceImpl) {
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderServiceImpl = orderServiceImpl;
    }

    public SubCategoryEntity convertToSubCategoryEntity(SubCategoryDto subCategoryDto) {
        String name = subCategoryDto.getName();
        CategoryDto categoryDto = subCategoryDto.getCategory();
        CategoryEntity categoryEntity = convertToCategoryEntity(categoryDto);
        return new SubCategoryEntity(name, categoryEntity);
    }

    public CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
        String name = categoryDto.getName();
        return new CategoryEntity(name);
    }

    public ProductEntity convertToProductEntity(ProductDto productDto) {
        SubCategoryDto subCategoryDto = productDto.getSubCategory();
        String name = subCategoryDto.getName();
        SubCategoryEntity subCategoryEntity = subCategoryRepository.getSubCategoryByName(name);
        String title = productDto.getTitle();
        Double price = productDto.getPrice();
        String disabledReason = productDto.getDisabledReason();
        ProductDescriptionEntity productDescriptionEntity = getProductDescriptionFromDto(productDto);
        ShipmentEntity shipmentEntity = getShipmentFromDto(productDto);
        return new ProductEntity(subCategoryEntity, title, price, false, disabledReason, false, productDescriptionEntity, shipmentEntity);
    }

    public ShipmentEntity getShipmentFromDto(ProductDto productDto) {
        ShipmentDto shipmentDto = productDto.getShipment();
        Long shipmentId = shipmentDto.getId();
        String shipmentDescription = shipmentDto.getDescription();
        String shipmentIncomeDate = shipmentDto.getIncomeDate();
        Timestamp shipmentDate = DateConverterUtil.convertStringDateToTimestamp(shipmentIncomeDate);
        ShipmentEntity shipmentEntity = new ShipmentEntity(shipmentDescription, shipmentDate);
        shipmentEntity.setId(shipmentId);
        return shipmentEntity;
    }

    public ProductDescriptionEntity getProductDescriptionFromDto(ProductDto productDto) {
        ProductDescriptionDto productDescriptionDto = productDto.getProductDescription();
        Long productDescriptionId = productDescriptionDto.getId();
        String nameDescription = productDescriptionDto.getName();
        String description = productDescriptionDto.getDescription();
        ProductDescriptionEntity productDescriptionEntity = new ProductDescriptionEntity(nameDescription, description);
        productDescriptionEntity.setId(productDescriptionId);
        return productDescriptionEntity;
    }

    public OrderEntity convertToOrderEntity(OrderDto orderDto) {
        String title = orderDto.getTitle();
        Double totalPriceOrder = orderServiceImpl.getTotalPriceOrder(orderDto);
        String creationDate = orderDto.getCreationDate();
        Timestamp timestampCreationDate = DateConverterUtil.convertStringDateToTimestamp(creationDate);
        List<UserProductDto> products = orderDto.getProducts();
        List<String> uuids = products.stream().map(item -> item.getUuid()).collect(Collectors.toList());
        List<ProductEntity> productEntities = productRepository.readProductsByUuids(uuids);
        Long userId = orderDto.getUserId();
        UserEntity userEntity = userRepository.readById(userId);
        return new OrderEntity(title, totalPriceOrder, timestampCreationDate, productEntities, userEntity);
    }
}
