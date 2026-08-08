package com.example.prison_management.mainuser;

import java.io.Serializable;

public class CapacityMonitoring implements Serializable {

    private String cellBlock;
    private int capacity;
    private int occupied;
    private int available;
    private String status;

    public CapacityMonitoring(String cellBlock,
                              int capacity,
                              int occupied,
                              int available,
                              String status) {

        this.cellBlock = cellBlock;
        this.capacity = capacity;
        this.occupied = occupied;
        this.available = available;
        this.status = status;
    }


    public String getCellBlock() {
        return cellBlock;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getAvailable() {
        return available;
    }

    public String getStatus() {
        return status;
    }


    public void setCellBlock(String cellBlock) {
        this.cellBlock = cellBlock;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "CapacityMonitoring{" +
                "cellBlock='" + cellBlock + '\'' +
                ", capacity=" + capacity +
                ", occupied=" + occupied +
                ", available=" + available +
                ", status='" + status + '\'' +
                '}';
    }
}