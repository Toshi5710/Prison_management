package com.example.prison_management.mainuser;

import java.io.Serializable;

public class VisitorRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private String visitorId;
    private String visitorName;
    private String prisonerName;
    private String visitDate;
    private String status;
    private String reviewComments;

    public VisitorRecords(String visitorId,
                          String visitorName,
                          String prisonerName,
                          String visitDate,
                          String status,
                          String reviewComments) {

        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.prisonerName = prisonerName;
        this.visitDate = visitDate;
        this.status = status;
        this.reviewComments = reviewComments;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    @Override
    public String toString() {
        return "VisitorRecords{" +
                "visitorId='" + visitorId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", prisonerName='" + prisonerName + '\'' +
                ", visitDate='" + visitDate + '\'' +
                ", status='" + status + '\'' +
                ", reviewComments='" + reviewComments + '\'' +
                '}';
    }
}