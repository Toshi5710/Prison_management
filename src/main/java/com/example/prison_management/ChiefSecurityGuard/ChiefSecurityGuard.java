package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.User;

import java.io.Serializable;

public class ChiefSecurityGuard extends User implements Serializable {


    private final String guardID;
    private String rank;
    private String assignedBlock;
    private String shift;


    public ChiefSecurityGuard(String userID, String password, String userRole, String guardID, String rank, String assignedBlock, String shift) {
        super(userID, password, userRole);
        this.guardID = guardID;
        this.rank = rank;
        this.assignedBlock = assignedBlock;
        this.shift = shift;
    }

    public String getGuardID() {
        return guardID;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAssignedBlock() {
        return assignedBlock;
    }

    public void setAssignedBlock(String assignedBlock) {
        this.assignedBlock = assignedBlock;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


    @Override
    public String toString() {
        return "ChiefSecurityGuard{" +
                "guardID='" + guardID + '\'' +
                ", rank='" + rank + '\'' +
                ", assignedBlock='" + assignedBlock + '\'' +
                ", shift='" + shift + '\'' +
                '}';
    }




}