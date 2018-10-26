package com.app.repository;

import com.app.model.MobilePhoneEntity;

import java.util.List;

public interface MobilePhoneRepository {

    MobilePhoneEntity save(MobilePhoneEntity product);
    MobilePhoneEntity readById(Long id);
    List<MobilePhoneEntity> readAllByModel(String model);
    List<MobilePhoneEntity> readAll();
    MobilePhoneEntity update(MobilePhoneEntity product);
    void delete(MobilePhoneEntity product);
    void deleteById(Long id);
}
