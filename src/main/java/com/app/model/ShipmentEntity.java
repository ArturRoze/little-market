package com.app.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "shipment")
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column(name = "income_date")
    private Timestamp incomeDate;

    public ShipmentEntity() {
    }

    public ShipmentEntity(String description, Timestamp incomeDate) {
        this.description = description;
        this.incomeDate = incomeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShipmentEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", incomeDate=" + incomeDate +
                '}';
    }
}
