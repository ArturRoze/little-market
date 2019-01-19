package com.app.controller;

import com.app.domain.income.OrderDto;
import com.app.model.OrderEntity;
import com.app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/orders")
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        LOGGER.info("create OrderController");
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity add(@RequestBody OrderDto orderDto) {
        LOGGER.info("income order: {}", orderDto);
        LOGGER.info("resultOfCreating: {}", orderService.addOrder(orderDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public List<OrderEntity> getUserOrders(@PathVariable Long id) {
        LOGGER.info("get all orders wih user id: {}", id);
        List<OrderEntity> allUserOrders = orderService.getAllUserOrders(id);
        LOGGER.info("get all user orders: {}", allUserOrders);
        return allUserOrders;
    }

    @GetMapping("/all")
    public List<OrderEntity> readAll() {
        LOGGER.info("read all orders...");
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderEntity readById(@PathVariable long id) {
        LOGGER.info("read order by id: ", id);
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody OrderDto orderDto, @PathVariable long id) {
        LOGGER.info("income order: {} with id: {}", orderDto, id);
        LOGGER.info("resultOfUpdate: {}", orderService.updateOrder(orderDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        LOGGER.info("income order with id: {}", id);
        orderService.deleteOrderById(id);
        LOGGER.info("Order with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
