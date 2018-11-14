package com.app.controller;

import com.app.domain.OrderDto;
import com.app.model.OrderEntity;
import com.app.model.ProductEntity;
import com.app.service.ProductService;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public UserController(ProductService productService, UserService userService) {
        LOGGER.info("create UserController");
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<ProductEntity> availableProducts(){
        LOGGER.info("get availableProducts...");
        List<ProductEntity> availableProducts = productService.getAvailableProducts();
        LOGGER.info("availableProducts: {}", availableProducts);
        return availableProducts;
    }

    @GetMapping("/{id}/orders")
    public List<OrderEntity> getAllUserOrders(@PathVariable Long id){
        LOGGER.info("get all orders wih user id: {}", id);
        List<OrderEntity> allUserOrders = userService.getAllUserOrders(id);
        LOGGER.info("user's orders: {}", allUserOrders);
        return allUserOrders;
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody OrderDto orderDto) {
        LOGGER.info("buy product : {}", orderDto);
        List<ProductEntity> productEntities = productService.buyProduct(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
