package com.app.repository.impl;

import com.app.model.ProductDescriptionEntity;
import com.app.model.ShipmentEntity;
import com.app.repository.ProductDescriptionRepository;
import com.app.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDescriptionRepositoryImpl implements ProductDescriptionRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public ProductDescriptionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public ProductDescriptionEntity save(ProductDescriptionEntity productDescriptionEntity) {
        LOGGER.info("save product description: {}", productDescriptionEntity);
        entityManager.persist(productDescriptionEntity);
        return productDescriptionEntity;
    }

    @Override
    @Transactional
    public ProductDescriptionEntity update(ProductDescriptionEntity productDescriptionEntity) {
        ProductDescriptionEntity mergedProductDescription = entityManager.merge(productDescriptionEntity);
        LOGGER.info("updated product description: " + mergedProductDescription);
        return mergedProductDescription;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDescriptionEntity> readAll() {
        TypedQuery<ProductDescriptionEntity> productDescriptionEntityTypedQuery = entityManager.createNamedQuery("get_all_product_descriptions", ProductDescriptionEntity.class);
        LOGGER.info("read all product descriptions: {} ", productDescriptionEntityTypedQuery);
        return productDescriptionEntityTypedQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDescriptionEntity readById(Long id) {
        LOGGER.info("read product description with id: {} ", id);
        return entityManager.find(ProductDescriptionEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDescriptionEntity getByName(String name) {
        LOGGER.info("get product description by name: {}", name);
        TypedQuery<ProductDescriptionEntity> getProductDescriptionByName = entityManager.createNamedQuery("get_product_description_by_name", ProductDescriptionEntity.class);
        getProductDescriptionByName.setParameter("name", name);
        return getProductDescriptionByName.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        ProductDescriptionEntity productDescriptionEntity = readById(id);
        if (productDescriptionEntity == null) {
            LOGGER.info("product description with id: {} not exist", id);
        }
        entityManager.remove(productDescriptionEntity);
        LOGGER.info("product description with id: {} was deleted", id);
    }
}
