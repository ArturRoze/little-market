package com.app.service;

import com.app.model.income.DescriptionDto;
import com.app.model.entity.ProductDescriptionEntity;

import java.util.List;

public interface ProductDescriptionService {

    ProductDescriptionEntity getByName(String name);

    boolean add(DescriptionDto descriptionDto);

    List<ProductDescriptionEntity> getAll();

    ProductDescriptionEntity getById(Long id);

    ProductDescriptionEntity update(DescriptionDto descriptionDto, long id);

    void deleteById(long id);
}
