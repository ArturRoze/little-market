package com.app.model;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column
    private Double priceOrder;
    @Column
    private Timestamp creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public OrderEntity() {
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

    public Double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(Double priceOrder) {
        this.priceOrder = priceOrder;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", count=" + count +
                ", priceOrder=" + priceOrder +
                ", creationDate=" + creationDate +
                ", user=" + user +
                '}';
    }
}
