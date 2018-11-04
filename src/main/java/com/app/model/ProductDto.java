package com.app.model;

import lombok.Data;

@Data
public class ProductDto {

    private String uuid;
    private SubCategoryDto subCategory;
    private String title;
    private Double price;
    private boolean disabled;
    private String disabledReason;
    private ProductDescriptionDto productDescription;
    private ShipmentDto shipment;

}
