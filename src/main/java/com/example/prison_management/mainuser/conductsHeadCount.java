package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class conductsHeadCount implements Serializable {

    int blockNumber;
    String shift;
    int prisonerCount;
    LocalDate date;

    public conductsHeadCount(int blockNumber, String shift, int prisonerCount, LocalDate date) {
        this.blockNumber = blockNumber;
        this.shift = shift;
        this.prisonerCount = prisonerCount;
        this.date = date;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getPrisonerCount() {
        return prisonerCount;
    }

    public void setPrisonerCount(int prisonerCount) {
        this.prisonerCount = prisonerCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "conductsHeadCount{" +
                "blockNumber=" + blockNumber +
                ", shift='" + shift + '\'' +
                ", prisonerCount=" + prisonerCount +
                ", date=" + date +
                '}';
    }
}
