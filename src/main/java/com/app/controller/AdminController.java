package com.app.controller;

import com.app.model.BlockRequest;
import com.app.model.MobilePhoneDto;
import com.app.service.BlockProduct;
import com.app.service.MobilePhoneService;
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
@RequestMapping("/admin")
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final MobilePhoneService mobilePhoneService;

    @Autowired
    public AdminController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody MobilePhoneDto mobilePhoneDto) {
        LOGGER.info("income request: {}", mobilePhoneDto);

        mobilePhoneService.addProduct(mobilePhoneDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/block")
    public ResponseEntity blockProduct(@RequestBody BlockRequest blockRequest) {
        LOGGER.info("income request: {}", blockRequest);


        List<Integer> ids = blockRequest.getIds();
        String blockReason = blockRequest.getBlockReason();
        mobilePhoneService.block(ids, blockReason);

        return new ResponseEntity(HttpStatus.OK);
    }
}
