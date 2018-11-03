package com.app.controller;

import com.app.model.ProductEntity;
import com.app.model.OrderEntity;
import com.app.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        LOGGER.info("create UserController");
        this.productService = productService;
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody OrderEntity orderEntity) {
        LOGGER.info("buy product : {}", orderEntity);
        List<ProductEntity> productEntities = productService.buyProduct(orderEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
