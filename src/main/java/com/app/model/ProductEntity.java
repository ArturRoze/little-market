package com.app.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(query = "select p from ProductEntity p join p.productDescriptionEntity join p.shipmentEntity", name = "get_all_products"),
        @NamedQuery(query = "select p from ProductEntity p where p.title = :title", name = "get_all_products_by_title"),
        @NamedQuery(query = "update ProductEntity p set p.disabled = true where p.id in :ids", name = "update_products_by_ids")
})
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column
    private String title;
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

    public ProductEntity() {
    }

    public ProductEntity(String uuid, String title, Double price, boolean disabled, boolean sold, ProductDescriptionEntity productDescriptionEntity, ShipmentEntity shipmentEntity) {
        this.uuid = uuid;
        this.title = title;
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
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return isDisabled() == that.isDisabled() &&
                isSold() == that.isSold() &&
                getId().equals(that.getId()) &&
                getUuid().equals(that.getUuid()) &&
                getTitle().equals(that.getTitle()) &&
                getPrice().equals(that.getPrice()) &&
                getProductDescriptionEntity().equals(that.getProductDescriptionEntity()) &&
                getShipmentEntity().equals(that.getShipmentEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getTitle(), getPrice(), isDisabled(), isSold(), getProductDescriptionEntity(), getShipmentEntity());
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", disabled=" + disabled +
                ", sold=" + sold +
                ", productDescriptionEntity=" + productDescriptionEntity +
                ", shipmentEntity=" + shipmentEntity +
                '}';
    }
}
