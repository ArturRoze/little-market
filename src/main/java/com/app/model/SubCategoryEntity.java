package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sub_category")
@NamedQueries({
        @NamedQuery(query = "select sc from SubCategoryEntity sc join sc.categoryEntity", name = "get_all_subCategories"),
        @NamedQuery(query = "select sc from SubCategoryEntity sc where sc.name = :name", name = "get_subCategory_by_name")
})
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private CategoryEntity categoryEntity;

    public SubCategoryEntity(String name, CategoryEntity categoryEntity) {
        this.name = name;
        this.categoryEntity = categoryEntity;
    }
}
