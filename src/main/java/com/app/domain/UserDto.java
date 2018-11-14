package com.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;
//    private String firstName;
//    private String lastName;
//    private String registrationDate;
//    private List<OrderDto> userOrders;
}
