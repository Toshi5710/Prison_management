package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleEntry implements Serializable {



    private final String licensePlate;
    private String driverName;
    private LocalDate entryDate;
    private int time;
    private String accessEntry;


    public String getLicensePlate() {
        return licensePlate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAccessEntry() {
        return accessEntry;
    }

    public void setAccessEntry(String accessEntry) {
        this.accessEntry = accessEntry;
    }

    public VehicleEntry(String licensePlate, String driverName, LocalDate entryDate, int time, String accessEntry) {
        this.licensePlate = licensePlate;
        this.driverName = driverName;
        this.entryDate = entryDate;
        this.time = time;
        this.accessEntry = accessEntry;



    }

    @Override
    public String toString() {
        return "VehicleEntry{" +
                "licensePlate='" + licensePlate + '\'' +
                ", driverName='" + driverName + '\'' +
                ", entryDate=" + entryDate +
                ", time=" + time +
                ", accessEntry='" + accessEntry + '\'' +
                '}';
    }
}
