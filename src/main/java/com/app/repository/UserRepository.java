package com.app.repository;

import com.app.model.UserEntity;

public interface UserRepository {

    UserEntity readById(Long id);
}
