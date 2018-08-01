package com.app.controller;

import com.app.model.MobilePhone;
import com.app.model.Order;
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

@RestController
@RequestMapping("/product")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final MobilePhoneService mobilePhoneService;

    @Autowired
    public UserController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    //TODO
    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody Order order) {
        LOGGER.info("buy product : {}", order);

        MobilePhone mobilePhone = mobilePhoneService.buyProduct(order);

        return new ResponseEntity(HttpStatus.OK);
    }


}
