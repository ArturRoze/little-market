package com.app.controller;

import com.app.domain.SubCategoryDto;
import com.app.model.SubCategoryEntity;
import com.app.service.SubCategoryService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SubCategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping("/new")
    public ResponseEntity addCategory(@RequestBody SubCategoryDto subCategoryDto) {
        LOGGER.info("income subCategory: {}", subCategoryDto);
        LOGGER.info("resultOfAdd: {}", subCategoryService.addSubCategory(subCategoryDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public SubCategoryEntity getSubCategoryByName(@PathVariable String name) {
        LOGGER.info("get subCategory by name: {}", name);
        if (!StringUtils.isNullOrEmpty(name)){
            return subCategoryService.getSubCategoryName(name);
        }
        return null;
    }

    @GetMapping("/all")
    public List<SubCategoryEntity> readAll() {
        LOGGER.info("read all categories...");
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{id}")
    public SubCategoryEntity readById(@PathVariable long id) {
        LOGGER.info("read subCategory by id: ", id);
        return subCategoryService.getSubCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody SubCategoryDto categoryDto, @PathVariable long id) {
        LOGGER.info("income subCategory: {} with id: {}", categoryDto, id);
        LOGGER.info("resultOfUpdate: {}", subCategoryService.updateSubCategory(categoryDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income subCategory with id: {}", id);
        subCategoryService.deleteById(id);
        LOGGER.info("SubCategory with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
