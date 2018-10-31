package com.app.model;

public class MobilePhoneDto {

    private String sku; //stock keeping unit
    private String model;
    private Double price;
    private ProductDescriptionDto productDescription;
    private ShipmentDto shipment;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
                "sku='" + sku + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", productDescriptionDto=" + productDescription +
                ", shipmentDto=" + shipment +
                '}';
    }
}
