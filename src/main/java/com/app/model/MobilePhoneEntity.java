package com.app.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mobile_phone")
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
    private String serialNumber;
    @Column
    private String model;
    @Column
    private Double price;
    @Column
    private boolean blocked;
    @Column
    private boolean isSold;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductDescriptionEntity productDescriptionEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    private ShipmentEntity shipmentEntity;

    public MobilePhoneEntity() {
    }

    public MobilePhoneEntity(String serialNumber, String model, Double price, boolean blocked, boolean isSold, ProductDescriptionEntity productDescriptionEntity, ShipmentEntity shipmentEntity) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.price = price;
        this.blocked = blocked;
        this.isSold = isSold;
        this.productDescriptionEntity = productDescriptionEntity;
        this.shipmentEntity = shipmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
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
        return isBlocked() == that.isBlocked() &&
                isSold() == that.isSold() &&
                getId().equals(that.getId()) &&
                getSerialNumber().equals(that.getSerialNumber()) &&
                getModel().equals(that.getModel()) &&
                getPrice().equals(that.getPrice()) &&
                getProductDescriptionEntity().equals(that.getProductDescriptionEntity()) &&
                getShipmentEntity().equals(that.getShipmentEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSerialNumber(), getModel(), getPrice(), isBlocked(), isSold(), getProductDescriptionEntity(), getShipmentEntity());
    }

    @Override
    public String toString() {
        return "MobilePhoneEntity{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", blocked=" + blocked +
                ", isSold=" + isSold +
                ", productDescriptionEntity=" + productDescriptionEntity +
                ", shipmentEntity=" + shipmentEntity +
                '}';
    }
}
