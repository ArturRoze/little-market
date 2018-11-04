package com.app.model;

import lombok.Data;

import java.util.List;

@Data
public class BlockRequestDto {

    private List<Long> ids;
    private String blockReason;
    private SubCategoryDto subCategory;
}
