package com.app.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private List<OrderDto> userOrders;
}
