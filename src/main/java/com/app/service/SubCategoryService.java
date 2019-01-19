package com.app.service;

import com.app.domain.income.SubCategoryDto;
import com.app.model.SubCategoryEntity;

import java.util.List;

public interface SubCategoryService {

    SubCategoryEntity getSubCategoryName(String name);

    boolean addSubCategory(SubCategoryDto subCategoryDto);

    List<SubCategoryEntity> getAllSubCategories();

    SubCategoryEntity getSubCategoryById(Long id);

    SubCategoryEntity updateSubCategory(SubCategoryDto categoryDto, Long id);

    void deleteById(Long id);
}
