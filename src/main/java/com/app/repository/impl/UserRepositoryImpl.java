package com.app.repository.impl;

import com.app.model.entity.UserEntity;
import com.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        LOGGER.info("save user: {}", userEntity);
        entityManager.persist(userEntity);
        return userEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity readUserById(Long id) {
        LOGGER.info("get user by id: {}", id);
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity readUserByLogin(String login) {
        LOGGER.info("read user with login: {} ", login);
        TypedQuery<UserEntity> userEntity = entityManager.createNamedQuery("get_user_by_login", UserEntity.class);
        userEntity.setParameter("login", login);
        LOGGER.info("read user: {} ", userEntity);
        return userEntity.getSingleResult();
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity mergedUser = entityManager.merge(userEntity);
        LOGGER.info("updated user: " + mergedUser);
        return mergedUser;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity userEntity = readUserById(id);
        if (userEntity == null) {
            LOGGER.info("user with id: {} not exist", id);
        }
        entityManager.remove(userEntity);
        LOGGER.info("user with id: {} was deleted", id);
    }
}
