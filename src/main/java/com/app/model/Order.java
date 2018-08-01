package com.app.model;

public class Order {

    private Integer idProduct;

    private Integer count;

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idProduct=" + idProduct +
                ", count=" + count +
                '}';
    }
}
