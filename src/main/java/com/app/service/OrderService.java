package com.app.service;

import com.app.domain.OrderDto;
import com.app.model.OrderEntity;

import java.util.List;

public interface OrderService {

    boolean addOrder(OrderDto orderDto);

    List<OrderEntity> getAllUserOrders(Long id);
}
