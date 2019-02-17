package com.app.model.income;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {

    private String name;
    private List<SubCategoryDto> subCategories;
}
