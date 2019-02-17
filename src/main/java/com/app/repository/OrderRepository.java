package com.app.repository;

import com.app.model.entity.OrderEntity;

import java.util.List;

public interface OrderRepository {

    OrderEntity save(OrderEntity orderEntity);

    List<OrderEntity> readAll();

    List<OrderEntity> getAllOrdersByUserId(Long id);

    OrderEntity readById(Long id);

    OrderEntity update(OrderEntity orderEntity);

    void deleteById(Long id);
}
