package com.app.service.impl;

import com.app.model.ConverterToDto;
import com.app.model.income.CategoryDto;
import com.app.model.ConverterToEntity;
import com.app.model.income.SubCategoryDto;
import com.app.model.outcome.CategoryOutcomeDto;
import com.app.model.entity.CategoryEntity;
import com.app.model.entity.SubCategoryEntity;
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
    private final ConverterToDto converterToDto;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ConverterToEntity converterToEntity, ConverterToDto converterToDto) {
        this.categoryRepository = categoryRepository;
        this.converterToEntity = converterToEntity;
        this.converterToDto = converterToDto;
    }

    @Override
    public CategoryOutcomeDto getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.getCategoryByName(name);
        return converterToDto.convertToCategoryOutcomeDto(categoryEntity);
    }

    @Override
    public CategoryOutcomeDto addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = converterToEntity.convertToCategoryEntity(categoryDto);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        if (savedCategoryEntity.getId() != null){
            return converterToDto.convertToCategoryOutcomeDto(savedCategoryEntity);
        }
        return null;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.readAll();
    }

    @Override
    public CategoryOutcomeDto getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.readById(id);
        return converterToDto.convertToCategoryOutcomeDto(categoryEntity);
    }

    @Override
    public CategoryOutcomeDto updateCategory(CategoryDto categoryDto, long id) {
        CategoryEntity categoryEntityFromDb = categoryRepository.readById(id);
        changeFieldsCategoryDtoToCategoryEntity(categoryDto, categoryEntityFromDb);
        CategoryEntity updatedCategoryEntity = categoryRepository.update(categoryEntityFromDb);
        return converterToDto.convertToCategoryOutcomeDto(updatedCategoryEntity);
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


