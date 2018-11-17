package com.app.controller;

import com.app.model.SubCategoryEntity;
import com.app.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SubCategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/{name}")
    public SubCategoryEntity getSubCategoryByName(@PathVariable String name) {
        LOGGER.info("get subCategory by name: {}", name);
        return subCategoryService.getSubCategoryName(name);
    }
}
