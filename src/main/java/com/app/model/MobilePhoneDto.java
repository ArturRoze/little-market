package com.app.model;

public class MobilePhoneDto {

    private String serialNumber;

    private Integer group;

    private Double price;

    private ProductDescription productDescription;

    private Shipment shipment;

    public MobilePhoneDto() {
    }

    public MobilePhoneDto(String serialNumber, Integer group, Double price, ProductDescription productDescription, Shipment shipment) {
        this.serialNumber = serialNumber;
        this.group = group;
        this.price = price;
        this.productDescription = productDescription;
        this.shipment = shipment;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "MobilePhoneDto{" +
                "serialNumber='" + serialNumber + '\'' +
                ", group=" + group +
                ", price=" + price +
                ", productDescription=" + productDescription +
                ", shipment=" + shipment +
                '}';
    }
}
