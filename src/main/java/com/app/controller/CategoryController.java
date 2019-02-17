package com.app.controller;

import com.app.model.income.CategoryDto;
import com.app.model.outcome.CategoryOutcomeDto;
import com.app.model.entity.CategoryEntity;
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
    public ResponseEntity<CategoryOutcomeDto> addCategory(@RequestBody CategoryDto categoryDto) {
        LOGGER.info("income category: {}", categoryDto);
        CategoryOutcomeDto categoryOutcomeDto = categoryService.addCategory(categoryDto);
        LOGGER.info("resultOfAdd: {}", categoryOutcomeDto);
        return new ResponseEntity<>(categoryOutcomeDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<CategoryEntity> readAll() {
        LOGGER.info("read all categories...");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryOutcomeDto readById(@PathVariable long id) {
        LOGGER.info("read category by id: ", id);
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{name}")
    public CategoryOutcomeDto getByName(@PathVariable String name) {
        LOGGER.info("get category by name: {}", name);
        if (StringUtils.isNullOrEmpty(name)){
            return null;
        }
        return categoryService.getCategoryByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryOutcomeDto> update(@RequestBody CategoryDto categoryDto, @PathVariable long id) {
        LOGGER.info("income category: {} with id: {}", categoryDto, id);
        CategoryOutcomeDto categoryOutcomeDto = categoryService.updateCategory(categoryDto, id);
        LOGGER.info("resultOfUpdate: {}", categoryOutcomeDto);
        return new ResponseEntity<>(categoryOutcomeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income category with id: {}", id);
        categoryService.deleteById(id);
        LOGGER.info("Category with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
