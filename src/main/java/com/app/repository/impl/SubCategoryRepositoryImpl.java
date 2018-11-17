package com.app.repository.impl;

import com.app.model.SubCategoryEntity;
import com.app.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class SubCategoryRepositoryImpl implements SubCategoryRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public SubCategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public SubCategoryEntity getSubCategoryByName(String name) {
        LOGGER.info("get subCategory by name: {}", name);
        TypedQuery<SubCategoryEntity> getSubCategoriesByName = entityManager.createNamedQuery("get_subCategory_by_name", SubCategoryEntity.class);
        getSubCategoriesByName.setParameter("name", name);
        return getSubCategoriesByName.getSingleResult();
    }
}
