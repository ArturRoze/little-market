package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(query = "select p from ProductEntity p left join p.productDescriptionEntity left join p.shipmentEntity", name = "get_all_products"),
        @NamedQuery(query = "select p from ProductEntity p where p.title = :title", name = "get_all_products_by_title"),
        @NamedQuery(query = "select p from ProductEntity p where p.uuid in :uuids", name = "get_products_by_uuids"),
        @NamedQuery(query = "update ProductEntity p set p.disabled = true, p.disabledReason = :description where p.id in :ids", name = "disable_products_by_ids_with_reason"),
        @NamedQuery(query = "update ProductEntity p set p.sold = true where p.uuid in :uuids", name = "sell_products")
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
}
