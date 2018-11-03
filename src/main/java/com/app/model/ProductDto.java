package com.app.model;

public class ProductDto {

    private String uuid;
    private String title;
    private Double price;
    private ProductDescriptionDto productDescription;
    private ShipmentDto shipment;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "ProductDto{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", productDescriptionDto=" + productDescription +
                ", shipmentDto=" + shipment +
                '}';
    }
}
