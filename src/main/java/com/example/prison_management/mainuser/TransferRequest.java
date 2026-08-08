package com.example.prison_management.mainuser;

import java.io.Serializable;

public class TransferRequest implements Serializable {

    private String requestId;
    private String prisonerId;
    private String transferType;
    private String reason;
    private String status;

    public TransferRequest() {
    }

    public TransferRequest(String requestId, String prisonerId,
                           String transferType, String reason,
                           String status) {
        this.requestId = requestId;
        this.prisonerId = prisonerId;
        this.transferType = transferType;
        this.reason = reason;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "requestId='" + requestId + '\'' +
                ", prisonerId='" + prisonerId + '\'' +
                ", transferType='" + transferType + '\'' +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}