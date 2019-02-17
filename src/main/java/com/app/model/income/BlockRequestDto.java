package com.app.model.income;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BlockRequestDto {

    private List<Long> ids;
    private String blockReason;
    private SubCategoryDto subCategory;
}
