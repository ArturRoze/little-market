package com.app.controller;

import com.app.model.BlockRequestEntity;
import com.app.model.MobilePhoneDto;
import com.app.model.MobilePhoneEntity;
import com.app.service.MobilePhoneService;
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
    private final MobilePhoneService mobilePhoneService;

    @Autowired
    public AdminController(MobilePhoneService mobilePhoneService) {
        LOGGER.info("create AdminController");
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody MobilePhoneDto mobilePhoneDto) {
        LOGGER.info("income request: {}", mobilePhoneDto);
        LOGGER.info("resultOfAdd: {}", mobilePhoneService.addProduct(mobilePhoneDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateProduct(@RequestBody MobilePhoneDto mobilePhoneDto, @PathVariable long id) {
        LOGGER.info("income request: {} with id: {}", mobilePhoneDto, id);
        LOGGER.info("resultOfUpdate: {}", mobilePhoneService.updateProduct(mobilePhoneDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/readAll")
    public List<MobilePhoneEntity> readAll() {
        LOGGER.info("read all product...");
        return mobilePhoneService.getAllProducts();
    }

    @GetMapping("/models/{model}")
    public List<MobilePhoneEntity> getAllProductsByModel(@PathVariable String model) {
        LOGGER.info("read all products by model: {}", model);
        return mobilePhoneService.getAllProductsByModel(model);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteProduct(@RequestBody MobilePhoneEntity mobilePhoneEntity) {
        LOGGER.info("income request: {}", mobilePhoneEntity);
        mobilePhoneService.deleteProduct(mobilePhoneEntity);
        LOGGER.info("Product was deleted");
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteProductById(@PathVariable long id) {
        LOGGER.info("income request with id: {}", id);
        mobilePhoneService.deleteById(id);
        LOGGER.info("Product with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/block")
    public ResponseEntity blockProduct(@RequestBody BlockRequestEntity blockRequestEntity) {
        LOGGER.info("income request: {}", blockRequestEntity);
        List<Long> ids = blockRequestEntity.getIds();
        String blockReason = blockRequestEntity.getBlockReason();
        mobilePhoneService.block(ids, blockReason);
        return new ResponseEntity(HttpStatus.OK);
    }
}
