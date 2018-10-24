package com.app.model;

import java.sql.Timestamp;

public class ShipmentDto {

    private Long id;

    private String description;

    private String incomeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {
        return "ShipmentDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", incomeDate=" + incomeDate +
                '}';
    }
}
