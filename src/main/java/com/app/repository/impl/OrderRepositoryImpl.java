package com.app.repository.impl;

import com.app.model.entity.OrderEntity;
import com.app.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public OrderEntity save(OrderEntity orderEntity) {
        LOGGER.info("save order: {}", orderEntity);
        entityManager.persist(orderEntity);
        return orderEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderEntity> readAll() {
        TypedQuery<OrderEntity> orderEntityTypedQuery = entityManager.createNamedQuery("get_all_orders", OrderEntity.class);
        LOGGER.info("read all category: {} ", orderEntityTypedQuery);
        return orderEntityTypedQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderEntity> getAllOrdersByUserId(Long id) {
        TypedQuery<OrderEntity> ordersByUserId = entityManager.createNamedQuery("get_all_orders_by_user_id", OrderEntity.class);
        ordersByUserId.setParameter("id", id);
        return ordersByUserId.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderEntity readById(Long id) {
        LOGGER.info("read order with id: {} ", id);
        return entityManager.find(OrderEntity.class, id);
    }

    @Override
    @Transactional
    public OrderEntity update(OrderEntity orderEntity) {
        OrderEntity mergeOrder = entityManager.merge(orderEntity);
        LOGGER.info("updated order: " + mergeOrder);
        return mergeOrder;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        OrderEntity orderEntity = readById(id);
        if (orderEntity == null) {
            LOGGER.info("order with id: {} not exist", id);
        }
        entityManager.remove(orderEntity);
        LOGGER.info("order with id: {} was deleted", id);
    }
}
