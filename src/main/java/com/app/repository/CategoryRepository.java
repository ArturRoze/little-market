package com.app.repository;

import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;

import java.util.List;

public interface CategoryRepository {

    SubCategoryEntity getSubCategoryByName(String name);

    CategoryEntity getCategoryByName(String name);
}
