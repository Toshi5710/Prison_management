package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class VisitorAppointment implements Serializable {

    private String name;
    private String nationalId;
    private String phone;
    private String prisoner;
    private LocalDate appointmentDate;

    public VisitorAppointment(String name,
                              String nationalId,
                              String phone,
                              String prisoner,
                              LocalDate appointmentDate) {

        this.name = name;
        this.nationalId = nationalId;
        this.phone = phone;
        this.prisoner = prisoner;
        this.appointmentDate = appointmentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(String prisoner) {
        this.prisoner = prisoner;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "VisitorAppointment{" +
                "name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phone='" + phone + '\'' +
                ", prisoner='" + prisoner + '\'' +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}