package com.example.prison_management.mainuser;

import java.io.Serializable;

public class Notification implements Serializable {

    private boolean emailNotification;
    private boolean smsNotification;

    public Notification(boolean emailNotification, boolean smsNotification) {
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public boolean isSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(boolean smsNotification) {
        this.smsNotification = smsNotification;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "emailNotification=" + emailNotification +
                ", smsNotification=" + smsNotification +
                '}';
    }
}