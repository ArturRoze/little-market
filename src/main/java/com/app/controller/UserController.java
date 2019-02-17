package com.app.controller;

import com.app.model.income.OrderDto;
import com.app.model.income.UserDto;
import com.app.model.outcome.UserOutcomeDto;
import com.app.service.ProductService;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<UserOutcomeDto> addUser(@RequestBody UserDto userDto) {
        LOGGER.info("income user: {}", userDto);
        UserOutcomeDto userOutcomeDto = userService.addUser(userDto);
        LOGGER.info("resultOfCreating response user: {}", userOutcomeDto);
        return new ResponseEntity<>(userOutcomeDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserOutcomeDto readUserById(@PathVariable long id) {
        LOGGER.info("read user by id: ", id);
        return userService.getUserById(id);
    }

    @GetMapping("/{login}")
    public UserOutcomeDto readUserByLogin(@PathVariable String login) {
        LOGGER.info("read user by login: ", login);
        return userService.getUserByLogin(login);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutcomeDto> updateUser(@RequestBody UserDto userDto, @PathVariable long id) {
        LOGGER.info("income user: {} with id: {}", userDto, id);
        UserOutcomeDto userOutcomeDto = userService.updateUser(userDto, id);
        LOGGER.info("resultOfUpdate: {}", userOutcomeDto);
        return new ResponseEntity<>(userOutcomeDto, HttpStatus.OK);
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
