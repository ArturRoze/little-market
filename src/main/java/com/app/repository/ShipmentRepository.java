package com.app.repository;

import com.app.model.ShipmentEntity;

import java.util.List;

public interface ShipmentRepository {

    ShipmentEntity save(ShipmentEntity shipmentEntity);

    ShipmentEntity update(ShipmentEntity shipmentEntity);

    List<ShipmentEntity> readAll();

    ShipmentEntity readById(Long id);

    ShipmentEntity getByDescription(String description);

    void deleteById(long id);
}
