package com.app.service;

import com.app.model.MobilePhoneDto;
import com.app.model.MobilePhoneEntity;
import com.app.model.OrderEntity;

import java.util.List;

public interface MobilePhoneService extends BlockProduct {

    List<MobilePhoneEntity> buyProduct(OrderEntity orderEntity);

    boolean addProduct(MobilePhoneDto mobilePhoneDto);

    MobilePhoneEntity updateProduct(MobilePhoneDto mobilePhoneDto, Long id);

    List<MobilePhoneEntity> getAllProducts();

    MobilePhoneEntity getProductById(Long id);

    List<MobilePhoneEntity> getAllProductsByModel(String model);

    void deleteById(Long id);
}
