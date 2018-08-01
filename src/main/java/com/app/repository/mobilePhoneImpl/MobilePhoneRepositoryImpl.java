package com.app.repository.mobilePhoneImpl;

import com.app.model.MobilePhone;
import com.app.repository.MobilePhoneRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO
@Repository
public class MobilePhoneRepositoryImpl implements MobilePhoneRepository {
    @Override
    public MobilePhone save(MobilePhone mobilePhone) {


        System.out.println(mobilePhone);
        return mobilePhone;
    }

    @Override
    public List<MobilePhone> readAll() {
        System.out.println("readAll");
        return null;
    }

    @Override
    public MobilePhone read(int id) {
        System.out.println("read product with id " + id);
        return null;
    }

    @Override
    public void update(int id) {
        System.out.println("update product with id " + id);
    }

    @Override
    public void delete(int id) {
        System.out.println("delete product with id " + id);
    }
}
