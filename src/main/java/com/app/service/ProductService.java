package com.app.service;

import com.app.model.OrderEntity;
import com.app.model.ProductDto;
import com.app.model.ProductEntity;

import java.util.List;

public interface ProductService extends BlockProduct {

    List<ProductEntity> buyProduct(OrderEntity orderEntity);

    boolean addProduct(ProductDto productDto);

    ProductEntity updateProduct(ProductDto productDto, Long id);

    List<ProductEntity> getAllProducts();

    ProductEntity getProductById(Long id);

    List<ProductEntity> getAllProductsByTitle(String title);

    void deleteById(Long id);
}
