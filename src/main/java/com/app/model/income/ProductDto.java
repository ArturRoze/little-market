package com.app.model.income;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String uuid;
    private SubCategoryDto subCategory;
    private String title;
    private Double price;
    private boolean disabled;
    private String disabledReason;
    private DescriptionDto description;
    private ShipmentDto shipment;

}
