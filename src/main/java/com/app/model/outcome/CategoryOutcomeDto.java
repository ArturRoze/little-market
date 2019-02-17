package com.app.model.outcome;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryOutcomeDto {

    private Long id;
    private String name;
    private List<SubCategoryOutcomeDto> subCategories;

    public CategoryOutcomeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
