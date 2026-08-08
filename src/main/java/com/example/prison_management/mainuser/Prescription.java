package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class Prescription implements Serializable {


    private final String prescriptionID;
    private String prisonerID;
    private String medicineName;
    private String dosage;
    private int duration;
    private LocalDate issueDate;


    public Prescription(String prescriptionID, String prisonerID, String medicineName, String dosage, int duration, LocalDate issueDate) {
        this.prescriptionID = prescriptionID;
        this.prisonerID = prisonerID;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.duration = duration;
        this.issueDate = issueDate;
    }


    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getPrisonerID() {
        return prisonerID;
    }

    public void setPrisonerID(String prisonerID) {
        this.prisonerID = prisonerID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionID='" + prescriptionID + '\'' +
                ", prisonerID='" + prisonerID + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", duration=" + duration +
                ", issueDate=" + issueDate +
                '}';
    }
}
