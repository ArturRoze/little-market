package com.app.repository;

import com.app.model.entity.ProductDescriptionEntity;

import java.util.List;

public interface ProductDescriptionRepository {

    ProductDescriptionEntity save(ProductDescriptionEntity productDescriptionEntity);

    ProductDescriptionEntity update(ProductDescriptionEntity productDescriptionEntity);

    List<ProductDescriptionEntity> readAll();

    ProductDescriptionEntity readById(Long id);

    ProductDescriptionEntity getByName(String name);

    void deleteById(long id);
}
