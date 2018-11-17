package com.app.service.impl;

import com.app.domain.CategoryDto;
import com.app.domain.SubCategoryDto;
import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;
import com.app.repository.SubCategoryRepository;
import com.app.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryServiceImpl categoryServiceImpl) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @Override
    public SubCategoryEntity getSubCategoryName(String name) {
        return subCategoryRepository.getSubCategoryByName(name);
    }

    @Override
    public boolean addSubCategory(SubCategoryDto subCategoryDto) {
        SubCategoryEntity categoryEntity = convertToSubCategoryEntity(subCategoryDto);
        SubCategoryEntity savedCategoryEntity = subCategoryRepository.save(categoryEntity);
        return savedCategoryEntity.getId() != null;
    }

    @Override
    public List<SubCategoryEntity> getAllSubCategories() {
        return subCategoryRepository.readAll();
    }

    @Override
    public SubCategoryEntity getSubCategoryById(Long id) {
        return subCategoryRepository.readById(id);
    }

    @Override
    public SubCategoryEntity updateSubCategory(SubCategoryDto subCategoryDto, Long id) {
        SubCategoryEntity subCategoryEntityFromDb = subCategoryRepository.readById(id);
        changeFieldsSubCategoryDtoToSubCategoryEntity(subCategoryDto, subCategoryEntityFromDb);
        return subCategoryRepository.update(subCategoryEntityFromDb);
    }

    private void changeFieldsSubCategoryDtoToSubCategoryEntity(SubCategoryDto subCategoryDto, SubCategoryEntity subCategoryEntityFromDb) {
        String name = subCategoryDto.getName();
        if (name != null){
            subCategoryEntityFromDb.setName(name);
        }
        CategoryDto categoryDto = subCategoryDto.getCategory();
        if (categoryDto != null){
            CategoryEntity categoryEntity = categoryServiceImpl.convertToCategoryEntity(categoryDto);
            subCategoryEntityFromDb.setCategoryEntity(categoryEntity);
        }
    }

    @Override
    public void deleteById(Long id) {
        subCategoryRepository.deleteById(id);
    }

    SubCategoryEntity convertToSubCategoryEntity(SubCategoryDto subCategoryDto) {
        String name = subCategoryDto.getName();
        CategoryDto categoryDto = subCategoryDto.getCategory();
        CategoryEntity categoryEntity = categoryServiceImpl.convertToCategoryEntity(categoryDto);
        return new SubCategoryEntity(name, categoryEntity);
    }
}
