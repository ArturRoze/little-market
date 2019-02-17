package com.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shipment")
@NamedQueries({
        @NamedQuery(query = "select sh from ShipmentEntity sh", name = "get_all_shipments"),
        @NamedQuery(query = "select sh from ShipmentEntity sh where sh.description = :description", name = "get_shipment_by_description")
})
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
