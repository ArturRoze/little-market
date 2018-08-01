package com.app.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mobile_phone")
public class MobilePhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String serialNumber;
    @Column
    private Integer group;
    @Column
    private Double price;
    @Column
    private boolean blocked;
    @ManyToOne
    private ProductDescription productDescription;
    @ManyToOne
    private Shipment shipment;

    public MobilePhone() {
    }

    public MobilePhone(String serialNumber, Integer group, Double price, boolean blocked, ProductDescription productDescription, Shipment shipment) {
        this.serialNumber = serialNumber;
        this.group = group;
        this.price = price;
        this.blocked = blocked;
        this.productDescription = productDescription;
        this.shipment = shipment;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobilePhone that = (MobilePhone) o;
        return blocked == that.blocked &&
                Objects.equals(id, that.id) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(group, that.group) &&
                Objects.equals(price, that.price) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(shipment, that.shipment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, serialNumber, group, price, blocked, productDescription, shipment);
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", group=" + group +
                ", price=" + price +
                ", blocked=" + blocked +
                ", productDescription=" + productDescription +
                ", shipment=" + shipment +
                '}';
    }
}
