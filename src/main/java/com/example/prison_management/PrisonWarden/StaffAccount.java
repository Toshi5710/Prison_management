package com.example.prison_management.PrisonWarden;

import java.io.Serializable;

public class StaffAccount implements Serializable {


    private String employeeId;
    private boolean activeStatus;

    public StaffAccount(String employeeId, boolean activeStatus) {
        this.employeeId = employeeId;
        this.activeStatus = activeStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "StaffAccount{" +
                "employeeId='" + employeeId + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}