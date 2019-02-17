package com.app.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(query = "select u from UserEntity u where u.login = :login", name = "get_user_by_login")
})
public class UserEntity extends BasicUser {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> userOrders;

    public UserEntity() {
    }

    public UserEntity(String login, String password, String email, Timestamp registrationDate, String firstName, String lastName) {
        super(login, password, email, registrationDate);
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
