package com.app.service;

import com.app.domain.UserDto;
import com.app.model.UserEntity;

public interface UserService {

    boolean addUser(UserDto userDto);

    UserEntity getUserById(Long id);

    UserEntity getUserByLogin(String login);

    UserEntity updateUser(UserDto userDto, Long id);

    void deleteUserById(Long id);
}
