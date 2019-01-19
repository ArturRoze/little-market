package com.app.service.impl;

import com.app.domain.ConverterToEntity;
import com.app.domain.income.UserDto;
import com.app.model.UserEntity;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConverterToEntity converterToEntity;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConverterToEntity converterToEntity) {
        this.userRepository = userRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public boolean addUser(UserDto userDto) {
        UserEntity userEntity = converterToEntity.convertToUserEntity(userDto);
        UserEntity savedUserEntity = userRepository.saveUser(userEntity);
        return savedUserEntity.getId() != null;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.readUserById(id);
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return userRepository.readUserByLogin(login);
    }

    @Override
    public UserEntity updateUser(UserDto userDto, Long id) {
        UserEntity userEntityFromDb = userRepository.readUserById(id);
        changeFieldsUserDtoToUserEntity(userDto, userEntityFromDb);
        return userRepository.updateUser(userEntityFromDb);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    private void changeFieldsUserDtoToUserEntity(UserDto userDto, UserEntity userEntityFromDb) {
        String password = userDto.getPassword();
        if (StringUtils.hasLength(password)) {
            userEntityFromDb.setPassword(password);
        }
        String email = userDto.getEmail();
        if (StringUtils.hasLength(email)) {
            userEntityFromDb.setEmail(email);
        }
        String firstName = userDto.getFirstName();
        if (StringUtils.hasLength(firstName)) {
            userEntityFromDb.setFirstName(firstName);
        }
        String lastName = userDto.getLastName();
        if (StringUtils.hasLength(lastName)) {
            userEntityFromDb.setLastName(lastName);
        }
    }
}
