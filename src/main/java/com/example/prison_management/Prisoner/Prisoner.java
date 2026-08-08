package com.example.prison_management.Prisoner;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prisoner implements Serializable {
    private String prisonerId, prisonerName , cellNumber, threatLevel;
    private LocalDate releaseDate;

    public Prisoner(String prisonerId, String prisonerName, String cellNumber, String threatLevel, LocalDate releaseDate) {
        this.prisonerId = prisonerId;
        this.prisonerName = prisonerName;
        this.cellNumber = cellNumber;
        this.threatLevel = threatLevel;
        this.releaseDate = releaseDate;


    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getThreatLevel() {
        return threatLevel;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "prisonerId='" + prisonerId + '\'' +
                ", prisonerName='" + prisonerName + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", threatLevel='" + threatLevel + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    public long getRemainingDays() {
        if (releaseDate == null) {
            return 0;
        }
        long days = ChronoUnit.DAYS.between(LocalDate.now(), releaseDate);
        if (days <= 0) {
            return 0;
        } else {
            return days;
        }
}}
