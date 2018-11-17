package com.app.repository.impl;

import com.app.model.CategoryEntity;
import com.app.model.SubCategoryEntity;
import com.app.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SubCategoryRepositoryImpl implements SubCategoryRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public SubCategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public SubCategoryEntity save(SubCategoryEntity subCategoryEntity) {
        LOGGER.info("save category: {}", subCategoryEntity);
        entityManager.persist(subCategoryEntity);
        return subCategoryEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoryEntity getSubCategoryByName(String name) {
        LOGGER.info("get subCategory by name: {}", name);
        TypedQuery<SubCategoryEntity> getSubCategoriesByName = entityManager.createNamedQuery("get_subCategory_by_name", SubCategoryEntity.class);
        getSubCategoriesByName.setParameter("name", name);
        return getSubCategoriesByName.getSingleResult();
    }

    @Override
    public List<SubCategoryEntity> readAll() {
        TypedQuery<SubCategoryEntity> subCategoryEntityTypedQuery = entityManager.createNamedQuery("get_all_subCategories", SubCategoryEntity.class);
        LOGGER.info("read all subCategory: {} ", subCategoryEntityTypedQuery);
        return subCategoryEntityTypedQuery.getResultList();
    }

    @Override
    public SubCategoryEntity readById(Long id) {
        LOGGER.info("read subCategory with id: {} ", id);
        return entityManager.find(SubCategoryEntity.class, id);
    }

    @Override
    public SubCategoryEntity update(SubCategoryEntity subCategoryEntity) {
        SubCategoryEntity mergeSubCategory = entityManager.merge(subCategoryEntity);
        LOGGER.info("updated subCategory: " + mergeSubCategory);
        return mergeSubCategory;
    }

    @Override
    public void deleteById(Long id) {
        SubCategoryEntity subCategoryEntity = readById(id);
        if (subCategoryEntity == null) {
            LOGGER.info("subCategory with id: {} not exist", id);
        }
        entityManager.remove(subCategoryEntity);
        LOGGER.info("subCategory with id: {} was deleted", id);
    }
}
