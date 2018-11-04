package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"subCategories"})
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column
    private String name;
    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SubCategoryEntity> subCategories;
}
