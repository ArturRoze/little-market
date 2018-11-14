package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
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

    public ShipmentEntity(String description, Timestamp incomeDate) {
        this.description = description;
        this.incomeDate = incomeDate;
    }
}
