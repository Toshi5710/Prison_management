package com.example.prison_management.mainuser;

import java.io.Serializable;

public class IdentityVerification implements Serializable {

    private String appointmentToken;
    private String verificationStatus;
    private String entryStatus;

    public IdentityVerification(String appointmentToken,
                                String verificationStatus,
                                String entryStatus) {

        this.appointmentToken = appointmentToken;
        this.verificationStatus = verificationStatus;
        this.entryStatus = entryStatus;
    }

    public String getAppointmentToken() {
        return appointmentToken;
    }

    public void setAppointmentToken(String appointmentToken) {
        this.appointmentToken = appointmentToken;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    @Override
    public String toString() {
        return "IdentityVerification{" +
                "appointmentToken='" + appointmentToken + '\'' +
                ", verificationStatus='" + verificationStatus + '\'' +
                ", entryStatus='" + entryStatus + '\'' +
                '}';
    }
}