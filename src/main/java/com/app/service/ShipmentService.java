package com.app.service;

import com.app.model.income.ShipmentDto;
import com.app.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentService {

    ShipmentEntity getByDescription(String description);

    boolean addShipment(ShipmentDto shipmentDto);

    List<ShipmentEntity> getAllShipments();

    ShipmentEntity getShipmentById(Long id);

    ShipmentEntity updateShipment(ShipmentDto shipmentDto, long id);

    void deleteById(long id);
}
