package com.example.prison_management.mainuser;

import java.io.Serializable;

public class Fine implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fineId;
    private String prisonerId;
    private float totalFine;
    private float outstandingBalance;
    private String paymentStatus; // "UNPAID", "PARTIAL", "PAID"

    public Fine() {}

    public Fine(String fineId, String prisonerId, float totalFine, float outstandingBalance, String paymentStatus) {
        this.fineId = fineId;
        this.prisonerId = prisonerId;
        this.totalFine = totalFine;
        this.outstandingBalance = outstandingBalance;
        this.paymentStatus = paymentStatus;
    }


    public boolean verifyOutstandingBalance() {
        return this.outstandingBalance > 0;
    }


    public void updateFinePaymentStatus(float paidAmount) {
        this.outstandingBalance -= paidAmount;
        if (this.outstandingBalance <= 0) {
            this.outstandingBalance = 0;
            this.paymentStatus = "PAID";
        } else {
            this.paymentStatus = "PARTIAL";
        }
    }


    public String getFineId() { return fineId; }
    public void setFineId(String fineId) { this.fineId = fineId; }

    public String getPrisonerId() { return prisonerId; }
    public void setPrisonerId(String prisonerId) { this.prisonerId = prisonerId; }

    public float getTotalFine() { return totalFine; }
    public void setTotalFine(float totalFine) { this.totalFine = totalFine; }

    public float getOutstandingBalance() { return outstandingBalance; }
    public void setOutstandingBalance(float outstandingBalance) { this.outstandingBalance = outstandingBalance; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}