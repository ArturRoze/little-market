package com.app.controller;

import com.app.domain.OrderDto;
import com.app.domain.UserDto;
import com.app.model.UserEntity;
import com.app.service.ProductService;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public UserController(ProductService productService, UserService userService) {
        LOGGER.info("create UserController");
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/new")
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        LOGGER.info("income user: {}", userDto);
        LOGGER.info("resultOfCreating: {}", userService.addUser(userDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserEntity readUserById(@PathVariable long id) {
        LOGGER.info("read user by id: ", id);
        return userService.getUserById(id);
    }

    @GetMapping("/{login}")
    public UserEntity readUserByLogin(@PathVariable String login) {
        LOGGER.info("read user by login: ", login);
        return userService.getUserByLogin(login);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto, @PathVariable long id) {
        LOGGER.info("income user: {} with id: {}", userDto, id);
        LOGGER.info("resultOfUpdate: {}", userService.updateUser(userDto, id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id) {
        LOGGER.info("delete user with id: {}", id);
        userService.deleteUserById(id);
        LOGGER.info("User with id: {} was deleted", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestBody OrderDto orderDto) {
        LOGGER.info("buy product : {}", orderDto);
        productService.buyProduct(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
