package com.app.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_description")
@NamedQueries({
        @NamedQuery(query = "select pd from ProductDescriptionEntity pd", name = "get_all_product_descriptions"),
        @NamedQuery(query = "select pd from ProductDescriptionEntity pd where pd.name = :name", name = "get_product_description_by_name")
})
public class ProductDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    public ProductDescriptionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
