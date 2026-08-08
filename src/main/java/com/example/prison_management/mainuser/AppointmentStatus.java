package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class AppointmentStatus implements Serializable {

    private String token;
    private String prisoner;
    private LocalDate visitDate;
    private String status;

    public AppointmentStatus(String token,
                             String prisoner,
                             LocalDate visitDate,
                             String status) {

        this.token = token;
        this.prisoner = prisoner;
        this.visitDate = visitDate;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(String prisoner) {
        this.prisoner = prisoner;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentStatus{" +
                "token='" + token + '\'' +
                ", prisoner='" + prisoner + '\'' +
                ", visitDate=" + visitDate +
                ", status='" + status + '\'' +
                '}';
    }
}