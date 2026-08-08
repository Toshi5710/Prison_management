package com.example.prison_management.mainuser;

import java.io.Serializable;

public class ShiftLeaveRequest implements Serializable {



    private final String requestID;
    private final String officerID;
    private String leaveReason;
    private String status;


    public ShiftLeaveRequest(String requestID, String officerID, String leaveReason, String status) {
        this.requestID = requestID;
        this.officerID = officerID;
        this.leaveReason = leaveReason;
        this.status = status;
    }


    public String getRequestID() {
        return requestID;
    }

    public String getOfficerID() {
        return officerID;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShiftLeaveRequest{" +
                "requestID='" + requestID + '\'' +
                ", officerID='" + officerID + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
