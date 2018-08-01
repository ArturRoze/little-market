package com.app.service.serviceImpl;

import com.app.model.*;
import com.app.repository.MobilePhoneRepository;
import com.app.service.BlockProduct;
import com.app.service.MobilePhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobilePhoneServiceImpl implements MobilePhoneService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final MobilePhoneRepository mobilePhoneRepository;

    @Autowired
    public MobilePhoneServiceImpl(MobilePhoneRepository mobilePhoneRepository) {
        this.mobilePhoneRepository = mobilePhoneRepository;
    }

    @Override
    public MobilePhone buyProduct(Order order) {

        return null;
    }

    @Override
    public boolean addProduct(MobilePhoneDto mobilePhoneDto) {

        MobilePhone mobilePhone = convertToMobilePhoneEntity(mobilePhoneDto);
        MobilePhone savedMobilePhone = mobilePhoneRepository.save(mobilePhone);
        return savedMobilePhone.getId() != null;
    }

    private MobilePhone convertToMobilePhoneEntity(MobilePhoneDto mobilePhoneDto) {
        String serialNumber = mobilePhoneDto.getSerialNumber();
        Double price = mobilePhoneDto.getPrice();
        ProductDescription productDescription = mobilePhoneDto.getProductDescription();
        Integer group = mobilePhoneDto.getGroup();
        Shipment shipment = mobilePhoneDto.getShipment();
        return new MobilePhone(serialNumber, group, price, false, productDescription, shipment);
    }

    @Override
    public boolean block(List<Integer> ids, String description) {


        return false;
    }
}
