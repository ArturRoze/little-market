package com.app.model.income;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private String title;
    private String creationDate;
    private List<UserProductDto> products;
    private Long userId;
}
