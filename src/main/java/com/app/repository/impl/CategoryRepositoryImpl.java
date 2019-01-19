package com.app.repository.impl;

import com.app.model.CategoryEntity;
import com.app.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    @Autowired
    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryEntity getCategoryByName(String name) {
        LOGGER.info("get category by name: {}", name);
        TypedQuery<CategoryEntity> getCategoryByName = entityManager.createNamedQuery("get_category_by_name", CategoryEntity.class);
        getCategoryByName.setParameter("name", name);
        return getCategoryByName.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryEntity readById(Long id) {
        LOGGER.info("read category with id: {} ", id);
        return entityManager.find(CategoryEntity.class, id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        CategoryEntity categoryEntity = readById(id);
        if (categoryEntity == null) {
            LOGGER.info("category with id: {} not exist", id);
        }
        entityManager.remove(categoryEntity);
        LOGGER.info("category with id: {} was deleted", id);
    }

    @Override
    @Transactional
    public CategoryEntity save(CategoryEntity categoryEntity) {
        LOGGER.info("save category: {}", categoryEntity);
        entityManager.persist(categoryEntity);
        return categoryEntity;
    }

    @Override
    @Transactional
    public CategoryEntity update(CategoryEntity categoryEntity) {
        CategoryEntity mergeCategory = entityManager.merge(categoryEntity);
        LOGGER.info("updated category: " + mergeCategory);
        return mergeCategory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryEntity> readAll() {
        TypedQuery<CategoryEntity> categoryEntityTypedQuery = entityManager.createNamedQuery("get_all_categories", CategoryEntity.class);
        LOGGER.info("read all category: {} ", categoryEntityTypedQuery);
        return categoryEntityTypedQuery.getResultList();
    }
}
