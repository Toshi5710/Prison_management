package com.example.prison_management.PrisonWarden;

import java.io.Serializable;

public class StaffWarning implements Serializable {

    private String employeeId, warningText;

    public StaffWarning(String employeeId, String warningText) {
        this.employeeId = employeeId;
        this.warningText = warningText;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    @Override
    public String toString() {
        return "StaffWarning{" +
                "employeeId='" + employeeId + '\'' +
                ", warningText='" + warningText + '\'' +
                '}';
    }
}