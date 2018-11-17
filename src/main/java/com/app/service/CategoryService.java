package com.app.service;

import com.app.domain.CategoryDto;
import com.app.model.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity getCategoryByName(String name);

    boolean addCategory(CategoryDto categoryDto);

    List<CategoryEntity> getAllCategories();

    CategoryEntity getCategoryById(Long id);

    CategoryEntity updateCategory(CategoryDto categoryDto, long id);

    void deleteById(long id);
}
