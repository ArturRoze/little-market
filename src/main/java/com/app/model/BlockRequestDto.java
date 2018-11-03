package com.app.model;

import java.util.List;

public class BlockRequestDto {

    private List<Long> ids;
    private String blockReason;
    private SubCategoryDto subCategory;

    public BlockRequestDto() {
    }

    public BlockRequestDto(List<Long> ids, String blockReason, SubCategoryDto subCategory) {
        this.ids = ids;
        this.blockReason = blockReason;
        this.subCategory = subCategory;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public SubCategoryDto getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDto subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "BlockRequestDto{" +
                "ids=" + ids +
                ", blockReason='" + blockReason + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}
