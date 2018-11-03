package com.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BasicUser {

    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> userOrders;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<OrderEntity> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<OrderEntity> userOrders) {
        this.userOrders = userOrders;
    }
}
