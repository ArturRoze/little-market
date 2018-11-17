package com.app.service;

import com.app.domain.OrderDto;
import com.app.domain.ProductDto;
import com.app.model.ProductEntity;

import java.util.List;

public interface ProductService extends BlockProduct {

    void buyProduct(OrderDto orderDto);

    boolean addProduct(ProductDto productDto);

    ProductEntity updateProduct(ProductDto productDto, Long id);

    List<ProductEntity> getAllProducts();

    List<ProductEntity> getAvailableProducts();

    ProductEntity getProductById(Long id);

    List<ProductEntity> getAllProductsByTitle(String title);

    void deleteById(Long id);
}
