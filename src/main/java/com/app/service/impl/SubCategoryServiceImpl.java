package com.app.service.impl;

import com.app.model.SubCategoryEntity;
import com.app.repository.SubCategoryRepository;
import com.app.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategoryEntity getSubCategoryName(String name) {
        return subCategoryRepository.getSubCategoryByName(name);
    }
}
