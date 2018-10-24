package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BasicUser {

    @Column
    private String firstName;
    @Column
    private String lastName;
}
