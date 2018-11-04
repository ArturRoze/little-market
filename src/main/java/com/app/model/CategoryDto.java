package com.app.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private List<SubCategoryDto> subCategories;
}
