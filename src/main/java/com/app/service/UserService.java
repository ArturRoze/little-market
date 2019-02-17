package com.app.service;

import com.app.model.income.UserDto;
import com.app.model.outcome.UserOutcomeDto;

public interface UserService {

    UserOutcomeDto addUser(UserDto userDto);

    UserOutcomeDto getUserById(Long id);

    UserOutcomeDto getUserByLogin(String login);

    UserOutcomeDto updateUser(UserDto userDto, Long id);

    void deleteUserById(Long id);
}
