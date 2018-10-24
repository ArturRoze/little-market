package com.app.model;

public class MobilePhoneDto {

    private String serialNumber;

    private Integer group;

    private String model;

    private Double price;

    private ProductDescriptionDto productDescription;

    private ShipmentDto shipment;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDescriptionDto getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescriptionDto productDescriptionDto) {
        this.productDescription = productDescriptionDto;
    }

    public ShipmentDto getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentDto shipmentDto) {
        this.shipment = shipmentDto;
    }

    @Override
    public String toString() {
        return "MobilePhoneDto{" +
                "serialNumber='" + serialNumber + '\'' +
                ", group=" + group +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", productDescriptionDto=" + productDescription +
                ", shipmentDto=" + shipment +
                '}';
    }
}
