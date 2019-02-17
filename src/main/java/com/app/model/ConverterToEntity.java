package com.app.model;

import com.app.model.entity.*;
import com.app.model.income.*;
import com.app.repository.ProductRepository;
import com.app.repository.SubCategoryRepository;
import com.app.repository.UserRepository;
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

    @Autowired
    public ConverterToEntity(SubCategoryRepository subCategoryRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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

        //добавлено как заглушка
        String name;
        if (subCategoryDto == null) {
            name = "coats";
        }else {
            name = subCategoryDto.getName();
        }

        SubCategoryEntity subCategoryEntity = subCategoryRepository.getSubCategoryByName(name);
        String title = productDto.getTitle();
        Double price = productDto.getPrice();
        String disabledReason = productDto.getDisabledReason();
        ProductDescriptionEntity productDescriptionEntity = getProductDescriptionFromDto(productDto);
        ShipmentEntity shipmentEntity = getShipmentFromProductDto(productDto);
        return new ProductEntity(subCategoryEntity, title, price, false, disabledReason, false, productDescriptionEntity, shipmentEntity);
    }

    public ShipmentEntity convertToShipmentEntity(ShipmentDto shipmentDto){
        String shipmentDescription = shipmentDto.getDescription();
        String shipmentIncomeDate = shipmentDto.getIncomeDate();
        Timestamp shipmentDate = DateConverterUtil.convertStringDateToTimestamp(shipmentIncomeDate);
        return new ShipmentEntity(shipmentDescription, shipmentDate);
    }

    public ShipmentEntity getShipmentFromProductDto(ProductDto productDto) {
        ShipmentDto shipmentDto = productDto.getShipment();

        //заглушка
        if (shipmentDto == null){
            return null;
        }

        return convertToShipmentEntity(shipmentDto);
    }

    public ProductDescriptionEntity convertToProductDescriptionEntity(DescriptionDto descriptionDto) {
        String name = descriptionDto.getName();
        String description = descriptionDto.getDescription();
        return new ProductDescriptionEntity(name, description);
    }

    public ProductDescriptionEntity getProductDescriptionFromDto(ProductDto productDto) {
        DescriptionDto descriptionDto = productDto.getDescription();
        //заглушка
        if (descriptionDto == null){
            return null;
        }

        return convertToProductDescriptionEntity(descriptionDto);
    }

    public OrderEntity convertToOrderEntity(OrderDto orderDto) {
        String title = orderDto.getTitle();
        Double totalPriceOrder = getTotalPriceOrder(orderDto);
        String creationDate = orderDto.getCreationDate();
        Timestamp timestampCreationDate = DateConverterUtil.convertStringDateToTimestamp(creationDate);
        List<UserProductDto> products = orderDto.getProducts();
        List<String> uuids = products.stream().map(item -> item.getUuid()).collect(Collectors.toList());
        List<ProductEntity> productEntities = productRepository.readProductsByUuids(uuids);
        Long userId = orderDto.getUserId();
        UserEntity userEntity = userRepository.readUserById(userId);
        return new OrderEntity(title, totalPriceOrder, timestampCreationDate, productEntities, userEntity);
    }

    public Double getTotalPriceOrder(OrderDto orderDto) {
        return orderDto.getProducts().stream().mapToDouble(item -> item.getPrice()).sum();
    }

    public UserEntity convertToUserEntity(UserDto userDto) {
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String creationDate = userDto.getCreationDate();
        Timestamp timestampCreationDate = DateConverterUtil.convertStringDateToTimestamp(creationDate);
        return new UserEntity(login, password, email, timestampCreationDate, firstName, lastName);
    }
}
