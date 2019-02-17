package com.app.repository.impl;

import com.app.model.entity.ShipmentEntity;
import com.app.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ShipmentRepositoryImpl implements ShipmentRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public ShipmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public ShipmentEntity save(ShipmentEntity shipmentEntity) {
        LOGGER.info("save shipment: {}", shipmentEntity);
        entityManager.persist(shipmentEntity);
        return shipmentEntity;
    }

    @Override
    @Transactional
    public ShipmentEntity update(ShipmentEntity shipmentEntity) {
        ShipmentEntity mergedShipment = entityManager.merge(shipmentEntity);
        LOGGER.info("updated shipment: " + mergedShipment);
        return mergedShipment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShipmentEntity> readAll() {
        TypedQuery<ShipmentEntity> shipmentEntityTypedQuery = entityManager.createNamedQuery("get_all_shipments", ShipmentEntity.class);
        LOGGER.info("read all shipments: {} ", shipmentEntityTypedQuery);
        return shipmentEntityTypedQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public ShipmentEntity readById(Long id) {
        LOGGER.info("read shipment with id: {} ", id);
        return entityManager.find(ShipmentEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ShipmentEntity getByDescription(String description) {
        LOGGER.info("get shipment by description: {}", description);
        TypedQuery<ShipmentEntity> getShipmentByDescription = entityManager.createNamedQuery("get_shipment_by_description", ShipmentEntity.class);
        getShipmentByDescription.setParameter("description", description);
        return getShipmentByDescription.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        ShipmentEntity shipmentEntity = readById(id);
        if (shipmentEntity == null) {
            LOGGER.info("shipment with id: {} not exist", id);
        }
        entityManager.remove(shipmentEntity);
        LOGGER.info("shipment with id: {} was deleted", id);
    }
}
