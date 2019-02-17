package com.app.repository;

import com.app.model.entity.UserEntity;

public interface UserRepository {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity readUserById(Long id);

    UserEntity readUserByLogin(String login);

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(Long id);
}
