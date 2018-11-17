package com.app.repository;

import com.app.model.CategoryEntity;

import java.util.List;

public interface CategoryRepository {

    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity update(CategoryEntity categoryEntity);

    List<CategoryEntity> readAll();

    CategoryEntity readById(Long id);

    CategoryEntity getCategoryByName(String name);

    void deleteById(long id);
}
