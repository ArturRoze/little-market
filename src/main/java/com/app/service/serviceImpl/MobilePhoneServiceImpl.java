package com.app.service.serviceImpl;

import com.app.model.*;
import com.app.repository.MobilePhoneRepository;
import com.app.service.MobilePhoneService;
import com.app.utils.DateConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public List<MobilePhoneEntity> buyProduct(OrderEntity orderEntity) {
        String modelOfProduct = orderEntity.getModel();
        Integer count = orderEntity.getCount();
        List<MobilePhoneEntity> orderedProduct = mobilePhoneRepository.readAllByModel(modelOfProduct);
//        if (orderedProduct.isEmpty()){
//            return orderedProduct;
//        }
        return orderedProduct;
    }

    @Override
    public boolean addProduct(MobilePhoneDto mobilePhoneDto) {
        MobilePhoneEntity mobilePhoneEntity = convertToMobilePhoneEntity(mobilePhoneDto);
        MobilePhoneEntity savedMobilePhoneEntity = mobilePhoneRepository.save(mobilePhoneEntity);
        return savedMobilePhoneEntity.getId() != null;
    }

    //TODO
    @Override
    public MobilePhoneEntity updateProduct(MobilePhoneDto mobilePhoneDto, Long id) {
        MobilePhoneEntity mobilePhoneEntityFromDb = mobilePhoneRepository.readById(id);
        String serialNumber = mobilePhoneDto.getSerialNumber();
        if (serialNumber != null){
            mobilePhoneEntityFromDb.setSerialNumber(serialNumber);
        }
        return mobilePhoneRepository.update(mobilePhoneEntityFromDb);
    }

    @Override
    public List<MobilePhoneEntity> getAllProducts() {
        return mobilePhoneRepository.readAll();
    }

    @Override
    public MobilePhoneEntity getProductById(Long id) {
        return mobilePhoneRepository.readById(id);
    }

    @Override
    public List<MobilePhoneEntity> getAllProductsByModel(String model) {
        return mobilePhoneRepository.readAllByModel(model);
    }

    @Override
    public void deleteById(Long id) {
        mobilePhoneRepository.deleteById(id);
    }

    //TODO
    @Override
    public int block(List<Long> ids, String description) {
        return mobilePhoneRepository.blockProductsWithIds(ids, description);

    }

    private MobilePhoneEntity convertToMobilePhoneEntity(MobilePhoneDto mobilePhoneDto) {
        String serialNumber = mobilePhoneDto.getSerialNumber();
        String model = mobilePhoneDto.getModel();
        Double price = mobilePhoneDto.getPrice();
        ProductDescriptionEntity productDescriptionEntity = getProductDescriptionFromDto(mobilePhoneDto);
        ShipmentEntity shipmentEntity = getShipmentFromDto(mobilePhoneDto);
        return new MobilePhoneEntity(serialNumber, model, price, false, false, productDescriptionEntity, shipmentEntity);
    }

    private ShipmentEntity getShipmentFromDto(MobilePhoneDto mobilePhoneDto) {
        ShipmentDto shipmentDto = mobilePhoneDto.getShipment();
        Long shipmentId = shipmentDto.getId();
        String shipmentDescription = shipmentDto.getDescription();
        String shipmentIncomeDate = shipmentDto.getIncomeDate();
        Timestamp shipmentDate = DateConverterUtil.convertStringDateToTimestamp(shipmentIncomeDate);
        ShipmentEntity shipmentEntity = new ShipmentEntity(shipmentDescription, shipmentDate);
        shipmentEntity.setId(shipmentId);
        return shipmentEntity;
    }

    private ProductDescriptionEntity getProductDescriptionFromDto(MobilePhoneDto mobilePhoneDto) {
        ProductDescriptionDto productDescriptionDto = mobilePhoneDto.getProductDescription();
        Long productDescriptionId = productDescriptionDto.getId();
        String nameDescription = productDescriptionDto.getName();
        String description = productDescriptionDto.getDescription();
        ProductDescriptionEntity productDescriptionEntity = new ProductDescriptionEntity(nameDescription, description);
        productDescriptionEntity.setId(productDescriptionId);
        return productDescriptionEntity;
    }
}
