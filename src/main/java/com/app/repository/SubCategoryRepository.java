package com.app.repository;

import com.app.model.SubCategoryEntity;

public interface SubCategoryRepository {

    SubCategoryEntity getSubCategoryByName(String name);

}
