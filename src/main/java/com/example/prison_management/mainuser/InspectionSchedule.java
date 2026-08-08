package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class InspectionSchedule implements Serializable {

    private LocalDate date;
    private String block;
    private String inspectionTeam;

    public InspectionSchedule(LocalDate date, String block, String inspectionTeam) {
        this.date = date;
        this.block = block;
        this.inspectionTeam = inspectionTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getInspectionTeam() {
        return inspectionTeam;
    }

    public void setInspectionTeam(String inspectionTeam) {
        this.inspectionTeam = inspectionTeam;
    }

    @Override
    public String toString() {
        return "InspectionSchedule{" +
                "date=" + date +
                ", block='" + block + '\'' +
                ", inspectionTeam='" + inspectionTeam + '\'' +
                '}';
    }
}