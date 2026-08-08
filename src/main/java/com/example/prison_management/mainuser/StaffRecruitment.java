package com.example.prison_management.mainuser;

import java.io.Serializable;

public class StaffRecruitment implements Serializable {

    private String candidateId;
    private String securityClearance;
    private String status;

    public StaffRecruitment(String candidateId, String securityClearance, String status) {
        this.candidateId = candidateId;
        this.securityClearance = securityClearance;
        this.status = status;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getSecurityClearance() {
        return securityClearance;
    }

    public void setSecurityClearance(String securityClearance) {
        this.securityClearance = securityClearance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StaffRecruitment{" +
                "candidateId='" + candidateId + '\'' +
                ", securityClearance='" + securityClearance + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}