package com.app.repository;

import com.app.model.OrderEntity;

import java.util.List;

public interface OrderRepository {

    OrderEntity save(OrderEntity orderEntity);

    List<OrderEntity> getAllOrdersByUserId(Long id);
}
