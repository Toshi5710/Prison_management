package com.example.prison_management.PrisonWarden;

import java.io.Serializable;


public class BudgetProposal implements Serializable {


        private final String proposalId;
;
        private String proposedAmount , justificationNarrative , targetFiscalYear , verificationStatus;


    public BudgetProposal(String proposalId, String proposedAmount, String justificationNarrative, String targetFiscalYear, String verificationStatus) {
        this.proposalId = proposalId;
        this.proposedAmount = proposedAmount;
        this.justificationNarrative = justificationNarrative;
        this.targetFiscalYear = targetFiscalYear;
        this.verificationStatus = verificationStatus;
    }

    public String getProposalId() {
        return proposalId;
    }

    public String getProposedAmount() {
        return proposedAmount;
    }

    public String getJustificationNarrative() {
        return justificationNarrative;
    }

    public String getTargetFiscalYear() {
        return targetFiscalYear;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setProposedAmount(String proposedAmount) {
        this.proposedAmount = proposedAmount;
    }

    public void setJustificationNarrative(String justificationNarrative) {
        this.justificationNarrative = justificationNarrative;
    }

    public void setTargetFiscalYear(String targetFiscalYear) {
        this.targetFiscalYear = targetFiscalYear;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    @Override
    public String toString() {
        return "BudgetProposal{" +
                "proposalId='" + proposalId + '\'' +
                ", proposedAmount=" + proposedAmount +
                ", justificationNarrative='" + justificationNarrative + '\'' +
                ", targetFiscalYear='" + targetFiscalYear + '\'' +
                ", verificationStatus='" + verificationStatus + '\'' +
                '}';
    }
}
