package com.app.service;

import com.app.domain.income.DescriptionDto;
import com.app.model.ProductDescriptionEntity;

import java.util.List;

public interface ProductDescriptionService {

    ProductDescriptionEntity getByName(String name);

    boolean add(DescriptionDto descriptionDto);

    List<ProductDescriptionEntity> getAll();

    ProductDescriptionEntity getById(Long id);

    ProductDescriptionEntity update(DescriptionDto descriptionDto, long id);

    void deleteById(long id);
}
