package com.app.controller;

import com.app.domain.ProductDto;
import com.app.exception.ProductException;
import com.app.model.ProductEntity;
import com.app.service.ProductService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductEntity> readAll() {
        LOGGER.info("read all product...");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductEntity readById(@PathVariable long id) {
        LOGGER.info("read product by id: ", id);
        return productService.getProductById(id);
    }

    @GetMapping("/{title}")
    public List<ProductEntity> getAllProductsByTitle(@PathVariable String title) {
        LOGGER.info("read all products by title: {}", title);
        if (!StringUtils.isNullOrEmpty(title)){
            return productService.getAllProductsByTitle(title);
        }
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        LOGGER.info("income request: {}", productDto);
        LOGGER.info("resultOfAdd: {}", productService.addProduct(productDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {
        LOGGER.info("income request: {} with id: {}", productDto, id);
        LOGGER.info("resultOfUpdate: {}", productService.updateProduct(productDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable long id) {
        LOGGER.info("income request with id: {}", id);
        productService.deleteById(id);
        LOGGER.info("Product with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/available")
    public List<ProductEntity> availableProducts() {
        LOGGER.info("get availableProducts...");
        List<ProductEntity> availableProducts = productService.getAvailableProducts();
        LOGGER.info("availableProducts: {}", availableProducts);
        return availableProducts;
    }


}
