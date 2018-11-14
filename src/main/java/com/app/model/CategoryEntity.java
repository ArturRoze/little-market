package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"subCategories"})
@NoArgsConstructor
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(query = "select c from CategoryEntity c where c.name = :name", name = "get_category_by_name")
})
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column
    private String name;
    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SubCategoryEntity> subCategories;

    public CategoryEntity(String name) {
        this.name = name;
    }
}
