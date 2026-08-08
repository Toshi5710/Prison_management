package com.example.prison_management.mainuser;

import java.io.Serializable;

public class PayrollAndPayments implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recordId;
    private String entityName; // e.g. Staff or Guard Name
    private String paymentType; // e.g. "SALARY"
    private float amount;
    private String paymentStatus; // e.g. "PAID"

    public PayrollAndPayments(String recordId, String entityName, String paymentType, float amount, String paymentStatus) {
        this.recordId = recordId;
        this.entityName = entityName;
        this.paymentType = paymentType;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }


    public String getRecordId() { return recordId; }
    public String getEntityName() { return entityName; }
    public String getPaymentType() { return paymentType; }
    public float getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }


    public void setRecordId(String recordId) { this.recordId = recordId; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
