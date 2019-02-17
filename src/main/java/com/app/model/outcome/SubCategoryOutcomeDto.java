package com.app.model.outcome;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubCategoryOutcomeDto {

    private Long id;
    private String name;
    private CategoryOutcomeDto category;

    public SubCategoryOutcomeDto(Long id, String name, CategoryOutcomeDto category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
