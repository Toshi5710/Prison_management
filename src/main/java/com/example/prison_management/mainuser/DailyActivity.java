package com.example.prison_management.mainuser;

import java.io.Serializable;

public class DailyActivity implements Serializable {

    private String prisonerId;
    private String prisonerName;
    private String activity;
    private String movement;
    private String guardReport;
    private String status;

    // Constructor
    public DailyActivity(String prisonerId,
                         String prisonerName,
                         String activity,
                         String movement,
                         String guardReport,
                         String status) {

        this.prisonerId = prisonerId;
        this.prisonerName = prisonerName;
        this.activity = activity;
        this.movement = movement;
        this.guardReport = guardReport;
        this.status = status;
    }

    // Getters
    public String getPrisonerId() {
        return prisonerId;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public String getActivity() {
        return activity;
    }

    public String getMovement() {
        return movement;
    }

    public String getGuardReport() {
        return guardReport;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public void setGuardReport(String guardReport) {
        this.guardReport = guardReport;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "DailyActivity{" +
                "prisonerId='" + prisonerId + '\'' +
                ", prisonerName='" + prisonerName + '\'' +
                ", activity='" + activity + '\'' +
                ", movement='" + movement + '\'' +
                ", guardReport='" + guardReport + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}