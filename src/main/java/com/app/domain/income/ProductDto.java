package com.app.domain.income;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
