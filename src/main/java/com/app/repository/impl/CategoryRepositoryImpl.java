package com.app.repository.impl;

import com.app.model.CategoryEntity;
import com.app.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        LOGGER.info("get category by name: {}", name);
        TypedQuery<CategoryEntity> getCategoryByName = entityManager.createNamedQuery("get_category_by_name", CategoryEntity.class);
        getCategoryByName.setParameter("name", name);
        return getCategoryByName.getSingleResult();
    }
}
