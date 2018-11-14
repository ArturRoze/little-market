package com.app.service;

import com.app.model.OrderEntity;
import com.app.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long id);

    List<OrderEntity> getAllUserOrders(Long id);
}
