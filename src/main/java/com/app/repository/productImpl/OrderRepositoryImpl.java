package com.app.repository.productImpl;

import com.app.model.OrderEntity;
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
    public List<OrderEntity> getAllOrdersByUserId(Long id) {
        TypedQuery<OrderEntity> ordersByUserId = entityManager.createNamedQuery("get_all_orders_by_user_id", OrderEntity.class);
        ordersByUserId.setParameter("id", id);
        return ordersByUserId.getResultList();
    }
}
