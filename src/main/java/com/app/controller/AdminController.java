package com.app.controller;

import com.app.domain.income.BlockRequestDto;
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
@RequestMapping("/admins")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        LOGGER.info("create AdminController");
        this.productService = productService;
    }

    @PostMapping("/disable")
    public ResponseEntity disableProduct(@RequestBody BlockRequestDto blockRequestDto) {
        LOGGER.info("income request: {}", blockRequestDto);
        List<Long> ids = blockRequestDto.getIds();
        String blockReason = blockRequestDto.getBlockReason();
        productService.block(ids, blockReason);
        return new ResponseEntity(HttpStatus.OK);
    }
}
