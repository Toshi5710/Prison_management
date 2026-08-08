package com.example.prison_management.PrisonWarden;

import java.io.Serializable;

public class Policy implements Serializable {

    private String policyId;
    private String description;

    public Policy(String policyId, String description) {
        this.policyId = policyId;
        this.description = description;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getDescription() {
        return description;
    }

    public void updateContent(String text) {
        if (text != null && !text.trim().isEmpty()) {
            this.description = text.trim();
        }
    }
}
