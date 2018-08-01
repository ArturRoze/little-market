package com.app.repository;

import com.app.model.MobilePhone;

import java.util.List;

public interface MobilePhoneRepository {

    MobilePhone save(MobilePhone product);

    List<MobilePhone> readAll();

    MobilePhone read(int id);

    void update(int id);

    void delete(int id);
}
