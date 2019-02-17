package com.app.model.income;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubCategoryDto {

    private String name;
    private CategoryDto category;
}
