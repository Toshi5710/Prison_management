package com.example.prison_management.mainuser;

import java.io.Serializable;

public class TransferRequest implements Serializable {

    private String prisonerID;
    private String destinationBlock;
    private String transferReason;

    public TransferRequest(String prisonerID, String destinationBlock, String transferReason) {
        this.prisonerID = prisonerID;
        this.destinationBlock = destinationBlock;
        this.transferReason = transferReason;
    }

    public String getPrisonerID() {
        return prisonerID;
    }

    public void setPrisonerID(String prisonerID) {
        this.prisonerID = prisonerID;
    }

    public String getDestinationBlock() {
        return destinationBlock;
    }

    public void setDestinationBlock(String destinationBlock) {
        this.destinationBlock = destinationBlock;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "prisonerID='" + prisonerID + '\'' +
                ", destinationBlock='" + destinationBlock + '\'' +
                ", transferReason='" + transferReason + '\'' +
                '}';
    }
}
