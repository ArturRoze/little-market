package com.app.model;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    public SubCategoryEntity() {
    }

    public SubCategoryEntity(String name, CategoryEntity categoryEntity) {
        this.name = name;
        this.categoryEntity = categoryEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    @Override
    public String toString() {
        return "SubCategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryEntity=" + categoryEntity +
                '}';
    }
}
