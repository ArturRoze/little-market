package com.app.repository.productImpl;

import com.app.model.SubCategoryEntity;
import com.app.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public SubCategoryEntity getSubCategoryByName(String name) {
        return null;
    }
}
