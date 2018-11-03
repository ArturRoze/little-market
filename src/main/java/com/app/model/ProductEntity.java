package com.app.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(query = "select p from ProductEntity p join p.productDescriptionEntity join p.shipmentEntity", name = "get_all_products"),
        @NamedQuery(query = "select p from ProductEntity p where p.title = :title", name = "get_all_products_by_title"),
        @NamedQuery(query = "update ProductEntity p set p.disabled = true, p.disabledReason = :description where p.id in :ids", name = "disable_products_by_ids_with_reason")
})
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne(cascade = CascadeType.ALL)
    private SubCategoryEntity subCategoryEntity;
    @Column
    private String title;
    @Column
    private Double price;
    @Column
    private boolean disabled;
    @Column(name = "disabled_reason")
    private String disabledReason;
    @Column
    private boolean sold;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductDescriptionEntity productDescriptionEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    private ShipmentEntity shipmentEntity;

    public ProductEntity() {
    }

    public ProductEntity(SubCategoryEntity subCategoryEntity, String title, Double price, boolean disabled, String disabledReason, boolean sold, ProductDescriptionEntity productDescriptionEntity, ShipmentEntity shipmentEntity) {
        this.subCategoryEntity = subCategoryEntity;
        this.title = title;
        this.price = price;
        this.disabled = disabled;
        this.disabledReason = disabledReason;
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

    public SubCategoryEntity getSubCategoryEntity() {
        return subCategoryEntity;
    }

    public void setSubCategoryEntity(SubCategoryEntity subCategoryEntity) {
        this.subCategoryEntity = subCategoryEntity;
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

    public String getDisabledReason() {
        return disabledReason;
    }

    public void setDisabledReason(String disabledReason) {
        this.disabledReason = disabledReason;
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
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return disabled == that.disabled &&
                sold == that.sold &&
                Objects.equals(id, that.id) &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(subCategoryEntity, that.subCategoryEntity) &&
                Objects.equals(title, that.title) &&
                Objects.equals(price, that.price) &&
                Objects.equals(disabledReason, that.disabledReason) &&
                Objects.equals(productDescriptionEntity, that.productDescriptionEntity) &&
                Objects.equals(shipmentEntity, that.shipmentEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, subCategoryEntity, title, price, disabled, disabledReason, sold, productDescriptionEntity, shipmentEntity);
    }
}
