package com.app.repository.mobilePhoneImpl;

import com.app.model.MobilePhoneEntity;
import com.app.repository.MobilePhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MobilePhoneRepositoryImpl implements MobilePhoneRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
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
    @Transactional(readOnly = true)
    public MobilePhoneEntity readById(Long id) {
        LOGGER.info("read product with id: {} ", id);
        return entityManager.find(MobilePhoneEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MobilePhoneEntity> readAllByModel(String model) {
        LOGGER.info("read product with model: {} ", model);
        TypedQuery<MobilePhoneEntity> mobilePhoneEntity = entityManager.createNamedQuery("get_all_mobilePhones_by_model", MobilePhoneEntity.class);
        mobilePhoneEntity.setParameter("model", model);
        LOGGER.info("read all product: {} ", mobilePhoneEntity);
        return mobilePhoneEntity.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MobilePhoneEntity> readAll() {
        TypedQuery<MobilePhoneEntity> mobilePhoneEntity = entityManager.createNamedQuery("get_all_mobilePhones", MobilePhoneEntity.class);
        LOGGER.info("read all product: {} ", mobilePhoneEntity);
        return mobilePhoneEntity.getResultList();
    }

    @Override
    @Transactional
    public MobilePhoneEntity update(MobilePhoneEntity mobilePhoneEntity) {
        MobilePhoneEntity mergeMobilePhone = entityManager.merge(mobilePhoneEntity);
        LOGGER.info("updated product: " + mergeMobilePhone);
        return mergeMobilePhone;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        MobilePhoneEntity mobilePhoneEntity = readById(id);
        if (mobilePhoneEntity != null) {
            entityManager.remove(mobilePhoneEntity);
            LOGGER.info("product with id: {} was deleted", id);
        } else {
            LOGGER.info("product with id: {} not exist", id);
        }
    }

    @Override
    @Transactional
    public int blockProductsWithIds(List<Long> ids, String description) {
        return entityManager.createNamedQuery("update_mobilePhones_by_ids").setParameter("ids", ids).executeUpdate();
    }
}
