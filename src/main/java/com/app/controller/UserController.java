package com.app.controller;

import com.app.domain.OrderDto;
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

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        LOGGER.info("create UserController");
        this.productService = productService;
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody OrderDto orderDto) {
        LOGGER.info("buy product : {}", orderDto);
        productService.buyProduct(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
