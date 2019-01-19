package com.app.domain.income;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private String name;
    private List<SubCategoryDto> subCategories;
}
