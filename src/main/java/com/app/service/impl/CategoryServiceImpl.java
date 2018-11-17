package com.app.service.impl;

import com.app.model.CategoryEntity;
import com.app.repository.CategoryRepository;
import com.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }
}
