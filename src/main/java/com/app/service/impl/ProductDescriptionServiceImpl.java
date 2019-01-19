package com.app.service.impl;

import com.app.domain.ConverterToEntity;
import com.app.domain.income.DescriptionDto;
import com.app.model.ProductDescriptionEntity;
import com.app.repository.ProductDescriptionRepository;
import com.app.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

    private final ProductDescriptionRepository productDescriptionRepository;
    private final ConverterToEntity converterToEntity;

    @Autowired
    public ProductDescriptionServiceImpl(ProductDescriptionRepository productDescriptionRepository, ConverterToEntity converterToEntity) {
        this.productDescriptionRepository = productDescriptionRepository;
        this.converterToEntity = converterToEntity;
    }

    @Override
    public ProductDescriptionEntity getByName(String name) {
        return productDescriptionRepository.getByName(name);
    }

    @Override
    public boolean add(DescriptionDto descriptionDto) {
        ProductDescriptionEntity productDescriptionEntity = converterToEntity.convertToProductDescriptionEntity(descriptionDto);
        ProductDescriptionEntity savedProductDescriptionEntity = productDescriptionRepository.save(productDescriptionEntity);
        return savedProductDescriptionEntity.getId() != null;
    }

    @Override
    public List<ProductDescriptionEntity> getAll() {
        return productDescriptionRepository.readAll();
    }

    @Override
    public ProductDescriptionEntity getById(Long id) {
        return productDescriptionRepository.readById(id);
    }

    @Override
    public ProductDescriptionEntity update(DescriptionDto descriptionDto, long id) {
        ProductDescriptionEntity productDescriptionEntityFromDb = productDescriptionRepository.readById(id);
        changeFieldsDescriptionDtoToProductDescriptionEntity(descriptionDto, productDescriptionEntityFromDb);
        return productDescriptionRepository.update(productDescriptionEntityFromDb);
    }

    @Override
    public void deleteById(long id) {
        productDescriptionRepository.deleteById(id);
    }

    private void changeFieldsDescriptionDtoToProductDescriptionEntity(DescriptionDto descriptionDto, ProductDescriptionEntity productDescriptionEntityFromDb) {
        String name = descriptionDto.getName();
        if (name != null) {
            productDescriptionEntityFromDb.setName(name);
        }
        String description = descriptionDto.getDescription();
        if (description != null) {
            productDescriptionEntityFromDb.setDescription(description);
        }
    }
}


