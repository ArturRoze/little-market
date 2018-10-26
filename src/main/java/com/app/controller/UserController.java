package com.app.controller;

import com.app.model.MobilePhoneEntity;
import com.app.model.OrderEntity;
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
@RequestMapping("/product")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final MobilePhoneService mobilePhoneService;

    @Autowired
    public UserController(MobilePhoneService mobilePhoneService) {
        LOGGER.info("create UserController");
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody OrderEntity orderEntity) {
        LOGGER.info("buy product : {}", orderEntity);
        List<MobilePhoneEntity> mobilePhoneEntities = mobilePhoneService.buyProduct(orderEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
