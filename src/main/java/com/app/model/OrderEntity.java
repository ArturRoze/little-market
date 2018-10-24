package com.app.model;

import javax.persistence.*;

@Entity
@Table(name = "client_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String model;
    @Column
    private Integer count;

    public OrderEntity() {
    }

    public OrderEntity(String model, Integer count) {
        this.model = model;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", count=" + count +
                '}';
    }
}
