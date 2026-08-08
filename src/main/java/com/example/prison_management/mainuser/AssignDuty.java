package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class AssignDuty implements Serializable {

    private String dutyArea;
    private String guard;
    private LocalDate date;
    private String shift;

    public AssignDuty(String dutyArea, String guard, LocalDate date, String shift) {
        this.dutyArea = dutyArea;
        this.guard = guard;
        this.date = date;
        this.shift = shift;
    }

    public String getDutyArea() {
        return dutyArea;
    }

    public void setDutyArea(String dutyArea) {
        this.dutyArea = dutyArea;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "AssignDuty{" +
                "dutyArea='" + dutyArea + '\'' +
                ", guard='" + guard + '\'' +
                ", date=" + date +
                ", shift='" + shift + '\'' +
                '}';
    }
}