package com.app.model;

import java.util.List;

public class OrderDto {

    private String title;
    private Integer count;
    private String creationDate;
    private List<ProductDto> products;
    private UserDto user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "title='" + title + '\'' +
                ", count=" + count +
                ", creationDate='" + creationDate + '\'' +
                ", products=" + products +
                ", user=" + user +
                '}';
    }
}
