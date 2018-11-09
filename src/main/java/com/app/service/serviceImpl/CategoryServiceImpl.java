package com.app.service.serviceImpl;

import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;
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
    public SubCategoryEntity getSubCategoryName(String name) {
        return categoryRepository.getSubCategoryByName(name);
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }
}
