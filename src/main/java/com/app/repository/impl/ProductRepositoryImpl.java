package com.app.repository.impl;

import com.app.model.entity.ProductEntity;
import com.app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity productEntity) {
        LOGGER.info("save product: {}", productEntity);
        entityManager.persist(productEntity);
        return productEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity readById(Long id) {
        LOGGER.info("read product with id: {} ", id);
        return entityManager.find(ProductEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> readAllByTitle(String title) {
        LOGGER.info("read product with title: {} ", title);
        TypedQuery<ProductEntity> productEntity = entityManager.createNamedQuery("get_all_products_by_title", ProductEntity.class);
        productEntity.setParameter("title", title);
        LOGGER.info("read all product: {} ", productEntity);
        return productEntity.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> readProductsByUuids(List<String> uuids) {
        LOGGER.info("read products with uuids: {} ", uuids);
        TypedQuery<ProductEntity> productByUuid = entityManager.createNamedQuery("get_products_by_uuids", ProductEntity.class);
        productByUuid.setParameter("uuids", uuids);
        LOGGER.info("read products: {} ", productByUuid);
        return productByUuid.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> readAll() {
        TypedQuery<ProductEntity> productEntity = entityManager.createNamedQuery("get_all_products", ProductEntity.class);
        LOGGER.info("read all product: {} ", productEntity);
        return productEntity.getResultList();
    }

    @Override
    @Transactional
    public ProductEntity update(ProductEntity productEntity) {
        ProductEntity mergeProduct = entityManager.merge(productEntity);
        LOGGER.info("updated product: " + mergeProduct);
        return mergeProduct;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ProductEntity productEntity = readById(id);
        if (productEntity == null) {
            LOGGER.info("product with id: {} not exist", id);
        }
        entityManager.remove(productEntity);
        LOGGER.info("product with id: {} was deleted", id);
    }

    @Override
    @Transactional
    public int blockProductsWithIds(List<Long> ids, String description) {
        return entityManager.createNamedQuery("disable_products_by_ids_with_reason")
                .setParameter("ids", ids)
                .setParameter("description", description)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void sellProducts(List<String> uuids) {
        entityManager.createNamedQuery("sell_products")
                .setParameter("uuids", uuids)
                .executeUpdate();
    }
}
