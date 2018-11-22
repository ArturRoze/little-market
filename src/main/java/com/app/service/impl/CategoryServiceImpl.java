package com.app.service.impl;

import com.app.domain.CategoryDto;
import com.app.domain.ConverterToEntity;
import com.app.domain.SubCategoryDto;
import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;
import com.app.repository.CategoryRepository;
import com.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConverterToEntity converterToEntity;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ConverterToEntity converterToEntity) {
        this.categoryRepository = categoryRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public boolean addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = converterToEntity.convertToCategoryEntity(categoryDto);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        return savedCategoryEntity.getId() != null;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.readAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.readById(id);
    }

    @Override
    public CategoryEntity updateCategory(CategoryDto categoryDto, long id) {
        CategoryEntity categoryEntityFromDb = categoryRepository.readById(id);
        changeFieldsCategoryDtoToCategoryEntity(categoryDto, categoryEntityFromDb);
        return categoryRepository.update(categoryEntityFromDb);
    }

    @Override
    public void deleteById(long id) {
        categoryRepository.deleteById(id);
    }

    private void changeFieldsCategoryDtoToCategoryEntity(CategoryDto categoryDto, CategoryEntity categoryEntityFromDb) {
        String name = categoryDto.getName();
        if (name != null){
            categoryEntityFromDb.setName(name);
        }
        List<SubCategoryDto> subCategoriesDto = categoryDto.getSubCategories();
        if (!subCategoriesDto.isEmpty()){
            List<SubCategoryEntity> subCategoryEntities = new ArrayList<>();
            for (SubCategoryDto subCategoryDto : subCategoriesDto) {
                subCategoryEntities.add(converterToEntity.convertToSubCategoryEntity(subCategoryDto));
            }
            categoryEntityFromDb.setSubCategories(subCategoryEntities);
        }
    }
}


