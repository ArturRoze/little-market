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
    private String title;
    @Column
    private Integer count;
    @Column
    private Double priceOrder;
    @Column
    private Timestamp creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public OrderEntity() {
    }

    public OrderEntity(String title, Integer count, Double priceOrder, Timestamp creationDate, UserEntity user) {
        this.title = title;
        this.count = count;
        this.priceOrder = priceOrder;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                ", title='" + title + '\'' +
                ", count=" + count +
                ", priceOrder=" + priceOrder +
                ", creationDate=" + creationDate +
                ", user=" + user +
                '}';
    }
}
