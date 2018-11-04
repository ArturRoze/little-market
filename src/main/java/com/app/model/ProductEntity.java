package com.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private SubCategoryEntity subCategoryEntity;
    @NonNull
    @Column
    private String title;
    @NonNull
    @Column
    private Double price;
    @NonNull
    @Column
    private boolean disabled;
    @NonNull
    @Column(name = "disabled_reason")
    private String disabledReason;
    @NonNull
    @Column
    private boolean sold;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductDescriptionEntity productDescriptionEntity;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private ShipmentEntity shipmentEntity;
}
