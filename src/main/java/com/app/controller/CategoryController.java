package com.app.controller;

import com.app.domain.income.CategoryDto;
import com.app.model.CategoryEntity;
import com.app.service.CategoryService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("categories")
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        LOGGER.info("create CategoryController");
        this.categoryService = categoryService;
    }

    @PostMapping("/new")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto) {
        LOGGER.info("income category: {}", categoryDto);
        LOGGER.info("resultOfAdd: {}", categoryService.addCategory(categoryDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CategoryEntity> readAll() {
        LOGGER.info("read all categories...");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryEntity readById(@PathVariable long id) {
        LOGGER.info("read category by id: ", id);
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{name}")
    public CategoryEntity getByName(@PathVariable String name) {
        LOGGER.info("get category by name: {}", name);
        if (StringUtils.isNullOrEmpty(name)){
            return null;
        }
        return categoryService.getCategoryByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CategoryDto categoryDto, @PathVariable long id) {
        LOGGER.info("income category: {} with id: {}", categoryDto, id);
        LOGGER.info("resultOfUpdate: {}", categoryService.updateCategory(categoryDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income category with id: {}", id);
        categoryService.deleteById(id);
        LOGGER.info("Category with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
