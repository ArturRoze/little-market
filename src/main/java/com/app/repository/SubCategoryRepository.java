package com.app.repository;

import com.app.model.entity.SubCategoryEntity;

import java.util.List;

public interface SubCategoryRepository {

    SubCategoryEntity save(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity getSubCategoryByName(String name);

    List<SubCategoryEntity> readAll();

    SubCategoryEntity readById(Long id);

    SubCategoryEntity update(SubCategoryEntity subCategoryEntity);

    void deleteById(Long id);
}
