package com.app.repository.mobilePhoneImpl;

import com.app.model.MobilePhoneEntity;
import com.app.repository.MobilePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MobilePhoneRepositoryImpl implements MobilePhoneRepository {

    private final EntityManager entityManager;

    @Autowired
    public MobilePhoneRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public MobilePhoneEntity save(MobilePhoneEntity mobilePhoneEntity) {
        entityManager.persist(mobilePhoneEntity);
        return mobilePhoneEntity;
    }

    @Override
    @Transactional
    public List<MobilePhoneEntity> readAll() {

        System.out.println("readAll");
        return null;
    }

    @Override
    @Transactional
    public List<MobilePhoneEntity> getAllByModel(String model) {
        System.out.println("read product with id " + model);
        return null;
    }

    @Override
    @Transactional
    public void update(int id) {
        System.out.println("update product with id " + id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        System.out.println("delete product with id " + id);
    }
}
