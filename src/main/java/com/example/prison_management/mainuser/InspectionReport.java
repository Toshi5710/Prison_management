package com.example.prison_management.mainuser;

import java.io.Serializable;
import java.time.LocalDate;

public class InspectionReport implements Serializable {



    private final String reportID;
    private int cellID;
    private LocalDate inspectionDate;
    private String status;
    private String remarks;


    public InspectionReport(String reportID, int cellID, LocalDate inspectionDate, String status, String remarks) {
        this.reportID = reportID;
        this.cellID = cellID;
        this.inspectionDate = inspectionDate;
        this.status = status;
        this.remarks = remarks;


    }





    public String getReportID() {
        return reportID;
    }

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "InspectionReport{" +
                "reportID='" + reportID + '\'' +
                ", cellID=" + cellID +
                ", inspectionDate=" + inspectionDate +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }


}
