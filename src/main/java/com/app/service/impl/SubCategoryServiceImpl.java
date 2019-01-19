package com.app.service.impl;

import com.app.domain.income.CategoryDto;
import com.app.domain.ConverterToEntity;
import com.app.domain.income.SubCategoryDto;
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
    private final ConverterToEntity converterToEntity;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, ConverterToEntity converterToEntity) {
        this.subCategoryRepository = subCategoryRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public SubCategoryEntity getSubCategoryName(String name) {
        return subCategoryRepository.getSubCategoryByName(name);
    }

    @Override
    public boolean addSubCategory(SubCategoryDto subCategoryDto) {
        SubCategoryEntity categoryEntity = converterToEntity.convertToSubCategoryEntity(subCategoryDto);
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
        if (name != null) {
            subCategoryEntityFromDb.setName(name);
        }
        CategoryDto categoryDto = subCategoryDto.getCategory();
        if (categoryDto != null) {
            CategoryEntity categoryEntity = converterToEntity.convertToCategoryEntity(categoryDto);
            subCategoryEntityFromDb.setCategoryEntity(categoryEntity);
        }
    }

    @Override
    public void deleteById(Long id) {
        subCategoryRepository.deleteById(id);
    }
}
