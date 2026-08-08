package com.example.prison_management.mainuser;

import java.io.Serializable;

public class EmergencyVisit implements Serializable {

    private String emergencyReason;
    private String documentPath;

    public EmergencyVisit(String emergencyReason, String documentPath) {
        this.emergencyReason = emergencyReason;
        this.documentPath = documentPath;
    }

    public String getEmergencyReason() {
        return emergencyReason;
    }

    public void setEmergencyReason(String emergencyReason) {
        this.emergencyReason = emergencyReason;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public String toString() {
        return "EmergencyVisit{" +
                "emergencyReason='" + emergencyReason + '\'' +
                ", documentPath='" + documentPath + '\'' +
                '}';
    }
}