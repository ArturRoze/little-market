package com.app.model;

public class ProductDto {

    private String uuid;
    private SubCategoryDto subCategory;
    private String title;
    private Double price;
    private String disabledReason;
    private ProductDescriptionDto productDescription;
    private ShipmentDto shipment;

    public ProductDto() {
    }

    public ProductDto(String uuid, SubCategoryDto subCategory, String title, Double price, String disabledReason, ProductDescriptionDto productDescription, ShipmentDto shipment) {
        this.uuid = uuid;
        this.subCategory = subCategory;
        this.title = title;
        this.price = price;
        this.disabledReason = disabledReason;
        this.productDescription = productDescription;
        this.shipment = shipment;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public SubCategoryDto getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDto subCategory) {
        this.subCategory = subCategory;
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

    public String getDisabledReason() {
        return disabledReason;
    }

    public void setDisabledReason(String disabledReason) {
        this.disabledReason = disabledReason;
    }

    public ProductDescriptionDto getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescriptionDto productDescription) {
        this.productDescription = productDescription;
    }

    public ShipmentDto getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentDto shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "uuid='" + uuid + '\'' +
                ", subCategory=" + subCategory +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", disabledReason='" + disabledReason + '\'' +
                ", productDescription=" + productDescription +
                ", shipment=" + shipment +
                '}';
    }
}
