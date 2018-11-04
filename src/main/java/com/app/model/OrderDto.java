package com.app.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private String title;
    private Integer count;
    private String creationDate;
    private List<ProductDto> products;
    private UserDto user;
}
