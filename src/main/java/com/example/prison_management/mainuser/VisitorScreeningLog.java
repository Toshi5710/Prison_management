package com.example.prison_management.mainuser;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

public class VisitorScreeningLog implements Serializable {


    private final String visitorID;

    private String visitorName, purpose, permission;

    private LocalDate visitingDate;


    public VisitorScreeningLog(String visitorID, String visitorName, String purpose, String permission, LocalDate visitingDate) {
        this.visitorID = visitorID;
        this.visitorName = visitorName;
        this.purpose = purpose;
        this.permission = permission;
        this.visitingDate = visitingDate;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(LocalDate visitingDate) {
        this.visitingDate = visitingDate;
    }


    @Override
    public String toString() {
        return "VisitorScreeningLog{" +
                "visitorID='" + visitorID + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", purpose='" + purpose + '\'' +
                ", permission='" + permission + '\'' +
                ", visitingDate=" + visitingDate +
                '}';
    }
}
