package com.app.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mobile_phone")
@NamedQueries({
        @NamedQuery(query = "select mph from MobilePhoneEntity mph join mph.productDescriptionEntity join mph.shipmentEntity", name = "get_all_mobilePhones"),
        @NamedQuery(query = "select mph from MobilePhoneEntity mph where mph.model = :model", name = "get_all_mobilePhones_by_model"),
        @NamedQuery(query = "update MobilePhoneEntity mph set mph.blocked = true where mph.id in :ids", name = "update_mobilePhones_by_ids")
})
public class MobilePhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String sku; //stock keeping unit
    @Column
    private String model;
    @Column
    private Double price;
    @Column
    private boolean disabled;
    @Column
    private boolean sold;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductDescriptionEntity productDescriptionEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    private ShipmentEntity shipmentEntity;

    public MobilePhoneEntity() {
    }

    public MobilePhoneEntity(String sku, String model, Double price, boolean disabled, boolean sold, ProductDescriptionEntity productDescriptionEntity, ShipmentEntity shipmentEntity) {
        this.sku = sku;
        this.model = model;
        this.price = price;
        this.disabled = disabled;
        this.sold = sold;
        this.productDescriptionEntity = productDescriptionEntity;
        this.shipmentEntity = shipmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public ProductDescriptionEntity getProductDescriptionEntity() {
        return productDescriptionEntity;
    }

    public void setProductDescriptionEntity(ProductDescriptionEntity productDescriptionEntity) {
        this.productDescriptionEntity = productDescriptionEntity;
    }

    public ShipmentEntity getShipmentEntity() {
        return shipmentEntity;
    }

    public void setShipmentEntity(ShipmentEntity shipmentEntity) {
        this.shipmentEntity = shipmentEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobilePhoneEntity)) return false;
        MobilePhoneEntity that = (MobilePhoneEntity) o;
        return isDisabled() == that.isDisabled() &&
                isSold() == that.isSold() &&
                getId().equals(that.getId()) &&
                getSku().equals(that.getSku()) &&
                getModel().equals(that.getModel()) &&
                getPrice().equals(that.getPrice()) &&
                getProductDescriptionEntity().equals(that.getProductDescriptionEntity()) &&
                getShipmentEntity().equals(that.getShipmentEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSku(), getModel(), getPrice(), isDisabled(), isSold(), getProductDescriptionEntity(), getShipmentEntity());
    }

    @Override
    public String toString() {
        return "MobilePhoneEntity{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", disabled=" + disabled +
                ", sold=" + sold +
                ", productDescriptionEntity=" + productDescriptionEntity +
                ", shipmentEntity=" + shipmentEntity +
                '}';
    }
}
