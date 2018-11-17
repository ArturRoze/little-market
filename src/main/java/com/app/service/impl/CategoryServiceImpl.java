package com.app.service.impl;

import com.app.domain.CategoryDto;
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
//    private final SubCategoryServiceImpl subCategoryServiceImpl;

//    @Autowired
//    public CategoryServiceImpl(CategoryRepository categoryRepository, SubCategoryServiceImpl subCategoryServiceImpl) {
//        this.categoryRepository = categoryRepository;
//        this.subCategoryServiceImpl = subCategoryServiceImpl;
//    }

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public boolean addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = convertToCategoryEntity(categoryDto);
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
//        List<SubCategoryDto> subCategoriesDto = categoryDto.getSubCategories();
//        if (!subCategoriesDto.isEmpty()){
//            List<SubCategoryEntity> subCategoryEntities = new ArrayList<>();
//            for (SubCategoryDto subCategoryDto : subCategoriesDto) {
//                subCategoryEntities.add(subCategoryServiceImpl.convertToSubCategoryEntity(subCategoryDto));
//            }
//            categoryEntityFromDb.setSubCategories(subCategoryEntities);
//        }
    }

    CategoryEntity convertToCategoryEntity(CategoryDto categoryDto) {
        String name = categoryDto.getName();
        return new CategoryEntity(name);
    }

}


