package com.app.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private String title;
    private String creationDate;
    private List<UserProductDto> products;
    private Long userId;
}
