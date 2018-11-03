package com.app.repository;

import com.app.model.SubCategoryEntity;

public interface CategoryRepository {

    SubCategoryEntity getSubCategoryByName(String name);
}
