package com.app.controller;

import com.app.model.income.DescriptionDto;
import com.app.model.entity.ProductDescriptionEntity;
import com.app.service.ProductDescriptionService;
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
@RequestMapping("descriptions")
public class ProductDescriptionController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductDescriptionService productDescriptionService;

    @Autowired
    public ProductDescriptionController(ProductDescriptionService productDescriptionService) {
        LOGGER.info("create ProductDescriptionController");
        this.productDescriptionService = productDescriptionService;
    }

    @PostMapping("/new")
    public ResponseEntity addCategory(@RequestBody DescriptionDto descriptionDto) {
        LOGGER.info("income product description: {}", descriptionDto);
        LOGGER.info("resultOfAdd: {}", productDescriptionService.add(descriptionDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ProductDescriptionEntity> readAll() {
        LOGGER.info("read all product descriptions...");
        return productDescriptionService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDescriptionEntity readById(@PathVariable long id) {
        LOGGER.info("read product description by id: ", id);
        return productDescriptionService.getById(id);
    }

    @GetMapping("/{name}")
    public ProductDescriptionEntity getByName(@PathVariable String name) {
        LOGGER.info("get product description by name: {}", name);
        if (StringUtils.isNullOrEmpty(name)) {
            return null;
        }
        return productDescriptionService.getByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody DescriptionDto descriptionDto, @PathVariable long id) {
        LOGGER.info("income product description: {} with id: {}", descriptionDto, id);
        LOGGER.info("resultOfUpdate: {}", productDescriptionService.update(descriptionDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income product description with id: {}", id);
        productDescriptionService.deleteById(id);
        LOGGER.info("Product description with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
