package com.app.model;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
abstract class BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String email;
    @Column (name = "registration_date")
    private Timestamp registrationDate;



}
