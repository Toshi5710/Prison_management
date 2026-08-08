package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalRecord implements Serializable {


    private final String recordID;
    private String prisonerID;
    private String diagnosis;
    private String treatment;
    private LocalDate recordDate;
    private String doctorNotes;


    public MedicalRecord(String recordID, String prisonerID, String diagnosis, String treatment, LocalDate recordDate, String doctorNotes) {
        this.recordID = recordID;
        this.prisonerID = prisonerID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.recordDate = recordDate;
        this.doctorNotes = doctorNotes;



    }

    public String getRecordID() {
        return recordID;
    }

    public String getPrisonerID() {
        return prisonerID;
    }

    public void setPrisonerID(String prisonerID) {
        this.prisonerID = prisonerID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordID='" + recordID + '\'' +
                ", prisonerID='" + prisonerID + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", recordDate=" + recordDate +
                ", doctorNotes='" + doctorNotes + '\'' +
                '}';
    }
}
