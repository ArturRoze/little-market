package com.app.service;

import com.app.model.MobilePhone;
import com.app.model.MobilePhoneDto;
import com.app.model.Order;

public interface MobilePhoneService extends BlockProduct{

    MobilePhone buyProduct(Order order);

    boolean addProduct(MobilePhoneDto mobilePhoneDto);


}
