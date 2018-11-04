package com.app.model;

import lombok.Data;

@Data
public class SubCategoryDto {

    private Long id;
    private String name;
    private CategoryDto category;

}
