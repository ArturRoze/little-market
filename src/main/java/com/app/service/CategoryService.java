package com.app.service;

import com.app.model.income.CategoryDto;
import com.app.model.outcome.CategoryOutcomeDto;
import com.app.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryOutcomeDto getCategoryByName(String name);

    CategoryOutcomeDto addCategory(CategoryDto categoryDto);

    List<CategoryEntity> getAllCategories();

    CategoryOutcomeDto getCategoryById(Long id);

    CategoryOutcomeDto updateCategory(CategoryDto categoryDto, long id);

    void deleteById(long id);
}
