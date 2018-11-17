package com.app.controller;

import com.app.model.CategoryEntity;
import com.app.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        LOGGER.info("create CategoryController");
        this.categoryService = categoryService;
    }

    @GetMapping("/{name}")
    public CategoryEntity getCategoryByName(@PathVariable String name) {
        LOGGER.info("get category by name: {}", name);
        return categoryService.getCategoryByName(name);
    }
}
