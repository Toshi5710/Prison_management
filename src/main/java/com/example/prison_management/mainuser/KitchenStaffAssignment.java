package com.example.prison_management.mainuser;

import java.io.Serializable;

public class KitchenStaffAssignment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String assignmentId;
    private String staffId;
    private String shiftPattern; // "MORNING", "EVENING", "NIGHT"
    private String assignedDuty;

    public KitchenStaffAssignment() {}

    public KitchenStaffAssignment(String assignmentId, String staffId, String shiftPattern, String assignedDuty) {
        this.assignmentId = assignmentId;
        this.staffId = staffId;
        this.shiftPattern = shiftPattern;
        this.assignedDuty = assignedDuty;
    }


    public boolean verifyAssignmentRequirements() {
        return shiftPattern != null && !shiftPattern.isEmpty() && assignedDuty != null;
    }


    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    public String getShiftPattern() { return shiftPattern; }
    public void setShiftPattern(String shiftPattern) { this.shiftPattern = shiftPattern; }

    public String getAssignedDuty() { return assignedDuty; }
    public void setAssignedDuty(String assignedDuty) { this.assignedDuty = assignedDuty; }
}
