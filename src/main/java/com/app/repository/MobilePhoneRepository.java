package com.app.repository;

import com.app.model.MobilePhoneEntity;

import java.util.List;

public interface MobilePhoneRepository {

    MobilePhoneEntity save(MobilePhoneEntity product);

    List<MobilePhoneEntity> readAll();

    List<MobilePhoneEntity> getAllByModel(String model);

    void update(int id);

    void delete(int id);
}
