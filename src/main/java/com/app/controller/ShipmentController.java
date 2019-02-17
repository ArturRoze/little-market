package com.app.controller;

import com.app.model.income.ShipmentDto;
import com.app.model.entity.ShipmentEntity;
import com.app.service.ShipmentService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("shipments")
public class ShipmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        LOGGER.info("create ShipmentController");
        this.shipmentService = shipmentService;
    }

    @PostMapping("/new")
    public ResponseEntity addShipment(@RequestBody ShipmentDto shipmentDto) {
        LOGGER.info("income shipment: {}", shipmentDto);
        LOGGER.info("resultOfAdd: {}", shipmentService.addShipment(shipmentDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ShipmentEntity> readAll() {
        LOGGER.info("read all shipments...");
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public ShipmentEntity readById(@PathVariable long id) {
        LOGGER.info("read shipment by id: ", id);
        return shipmentService.getShipmentById(id);
    }

    @GetMapping("/{description}")
    public ShipmentEntity getByDescription(@PathVariable String description) {
        LOGGER.info("get category by description: {}", description);
        if (StringUtils.isNullOrEmpty(description)){
            return null;
        }
        return shipmentService.getByDescription(description);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody ShipmentDto shipmentDto, @PathVariable long id) {
        LOGGER.info("income shipment: {} with id: {}", shipmentDto, id);
        LOGGER.info("resultOfUpdate: {}", shipmentService.updateShipment(shipmentDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income shipment with id: {}", id);
        shipmentService.deleteById(id);
        LOGGER.info("Shipment with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
