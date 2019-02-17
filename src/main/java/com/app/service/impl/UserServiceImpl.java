package com.app.service.impl;

import com.app.model.ConverterToDto;
import com.app.model.ConverterToEntity;
import com.app.model.income.UserDto;
import com.app.model.outcome.UserOutcomeDto;
import com.app.model.entity.UserEntity;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConverterToEntity converterToEntity;
    private final ConverterToDto converterToDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConverterToEntity converterToEntity, ConverterToDto converterToDto) {
        this.userRepository = userRepository;
        this.converterToEntity = converterToEntity;
        this.converterToDto = converterToDto;
    }

    @Override
    public UserOutcomeDto addUser(UserDto userDto) {
        UserEntity userEntity = converterToEntity.convertToUserEntity(userDto);
        UserEntity savedUserEntity = userRepository.saveUser(userEntity);
        if (savedUserEntity.getId() != null){
            return converterToDto.convertToUserOutcomeDto(savedUserEntity);
        }
        return null;
    }

    @Override
    public UserOutcomeDto getUserById(Long id) {
        UserEntity userEntity = userRepository.readUserById(id);
        return converterToDto.convertToUserOutcomeDto(userEntity);
    }

    @Override
    public UserOutcomeDto getUserByLogin(String login) {
        UserEntity userEntity = userRepository.readUserByLogin(login);
        return converterToDto.convertToUserOutcomeDto(userEntity);
    }

    @Override
    public UserOutcomeDto updateUser(UserDto userDto, Long id) {
        UserEntity userEntityFromDb = userRepository.readUserById(id);
        changeFieldsUserDtoToUserEntity(userDto, userEntityFromDb);
        UserEntity userEntity = userRepository.updateUser(userEntityFromDb);
        return converterToDto.convertToUserOutcomeDto(userEntity);
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
