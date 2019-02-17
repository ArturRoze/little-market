package com.app.service;

import com.app.model.income.SubCategoryDto;
import com.app.model.entity.SubCategoryEntity;

import java.util.List;

public interface SubCategoryService {

    SubCategoryEntity getSubCategoryName(String name);

    boolean addSubCategory(SubCategoryDto subCategoryDto);

    List<SubCategoryEntity> getAllSubCategories();

    SubCategoryEntity getSubCategoryById(Long id);

    SubCategoryEntity updateSubCategory(SubCategoryDto categoryDto, Long id);

    void deleteById(Long id);
}
