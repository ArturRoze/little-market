package com.app.service.impl;

import com.app.domain.ConverterToEntity;
import com.app.domain.income.ShipmentDto;
import com.app.model.ShipmentEntity;
import com.app.repository.ShipmentRepository;
import com.app.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ConverterToEntity converterToEntity;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ConverterToEntity converterToEntity) {
        this.shipmentRepository = shipmentRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public ShipmentEntity getByDescription(String description) {
        return shipmentRepository.getByDescription(description);
    }

    @Override
    public boolean addShipment(ShipmentDto shipmentDto) {
        ShipmentEntity categoryEntity = converterToEntity.convertToShipmentEntity(shipmentDto);
        ShipmentEntity savedCategoryEntity = shipmentRepository.save(categoryEntity);
        return savedCategoryEntity.getId() != null;
    }

    @Override
    public List<ShipmentEntity> getAllShipments() {
        return shipmentRepository.readAll();
    }

    @Override
    public ShipmentEntity getShipmentById(Long id) {
        return shipmentRepository.readById(id);
    }

    @Override
    public ShipmentEntity updateShipment(ShipmentDto shipmentDto, long id) {
        ShipmentEntity shipmentEntityFromDb = shipmentRepository.readById(id);
        changeFieldsShipmentDtoToShipmentEntity(shipmentDto, shipmentEntityFromDb);
        return shipmentRepository.update(shipmentEntityFromDb);
    }

    @Override
    public void deleteById(long id) {
        shipmentRepository.deleteById(id);
    }

    private void changeFieldsShipmentDtoToShipmentEntity(ShipmentDto shipmentDto, ShipmentEntity shipmentEntityFromDb) {
        String description = shipmentDto.getDescription();
        if (description != null) {
            shipmentEntityFromDb.setDescription(description);
        }
    }
}
