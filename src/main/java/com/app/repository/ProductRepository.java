package com.app.repository;

import com.app.model.ProductEntity;

import java.util.List;

public interface ProductRepository {

    ProductEntity save(ProductEntity product);

    ProductEntity readById(Long id);

    List<ProductEntity> readAllByTitle(String title);

    List<ProductEntity> readAll();

    ProductEntity update(ProductEntity product);

    void deleteById(Long id);

    int blockProductsWithIds(List<Long> ids, String description);
}
