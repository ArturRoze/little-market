package com.app.service;

import com.app.model.income.OrderDto;
import com.app.model.income.ProductDto;
import com.app.model.entity.ProductEntity;

import java.util.List;

public interface ProductService extends BlockProductService {

    void buyProduct(OrderDto orderDto);

    boolean addProduct(ProductDto productDto);

    ProductEntity updateProduct(ProductDto productDto, Long id);

    List<ProductEntity> getAllProducts();

    List<ProductEntity> getAvailableProducts();

    ProductEntity getProductById(Long id);

    List<ProductEntity> getAllProductsByTitle(String title);

    void deleteProductById(Long id);
}
