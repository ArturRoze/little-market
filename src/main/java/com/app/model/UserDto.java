package com.app.model;

import java.util.List;

public class UserDto {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private List<OrderDto> userOrders;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<OrderDto> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<OrderDto> userOrders) {
        this.userOrders = userOrders;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", userOrders=" + userOrders +
                '}';
    }
}
