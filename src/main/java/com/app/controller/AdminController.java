package com.app.controller;

import com.app.model.BlockRequestDto;
import com.app.model.ProductDto;
import com.app.model.ProductEntity;
import com.app.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        LOGGER.info("create AdminController");
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        LOGGER.info("income request: {}", productDto);
        LOGGER.info("resultOfAdd: {}", productService.addProduct(productDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {
        LOGGER.info("income request: {} with id: {}", productDto, id);
        LOGGER.info("resultOfUpdate: {}", productService.updateProduct(productDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/readAll")
    public List<ProductEntity> readAll() {
        LOGGER.info("read all product...");
        return productService.getAllProducts();
    }

    @GetMapping("/read/{id}")
    public ProductEntity readById(@PathVariable long id) {
        LOGGER.info("read product by id: ", id);
        return productService.getProductById(id);
    }

    @GetMapping("/titles/{title}")
    public List<ProductEntity> getAllProductsByModel(@PathVariable String title) {
        LOGGER.info("read all products by title: {}", title);
        return productService.getAllProductsByTitle(title);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteProductById(@PathVariable long id) {
        LOGGER.info("income request with id: {}", id);
        productService.deleteById(id);
        LOGGER.info("Product with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/block")
    public ResponseEntity blockProduct(@RequestBody BlockRequestDto blockRequestDto) {
        LOGGER.info("income request: {}", blockRequestDto);
        List<Long> ids = blockRequestDto.getIds();
        String blockReason = blockRequestDto.getBlockReason();
        productService.block(ids, blockReason);
        return new ResponseEntity(HttpStatus.OK);
    }
}
