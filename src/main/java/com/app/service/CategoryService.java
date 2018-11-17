package com.app.service;

import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity getCategoryByName(String name);
}
