package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class IncidentReport implements Serializable {

    private String incidentId;
    private LocalDate date;
    private String incidentType;
    private String description;

    public IncidentReport(String incidentId, LocalDate date, String incidentType, String description) {
        this.incidentId = incidentId;
        this.date = date;
        this.incidentType = incidentType;
        this.description = description;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IncidentReport{" +
                "incidentId='" + incidentId + '\'' +
                ", date=" + date +
                ", incidentType='" + incidentType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}