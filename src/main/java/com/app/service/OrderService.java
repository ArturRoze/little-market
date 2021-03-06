package com.app.service;

import com.app.model.income.OrderDto;
import com.app.model.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    boolean addOrder(OrderDto orderDto);

    List<OrderEntity> getAllUserOrders(Long id);

    List<OrderEntity> getAllOrders();

    OrderEntity getOrderById(Long id);

    OrderEntity updateOrder(OrderDto orderDto, Long id);

    void deleteOrderById(Long id);
}
