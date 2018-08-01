package com.app.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private Timestamp incomeDate;

    public Shipment() {
    }

    public Shipment(String description, Timestamp incomeDate) {
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
        return "Shipment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", incomeDate=" + incomeDate +
                '}';
    }
}
