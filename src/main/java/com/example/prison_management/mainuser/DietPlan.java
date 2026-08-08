package com.example.prison_management.mainuser;

import java.io.Serializable;

public class DietPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String planId;
    private String prisonerId;
    private String medicalRestrictions;

    public DietPlan() {}

    public DietPlan(String planId, String prisonerId, String medicalRestrictions) {
        this.planId = planId;
        this.prisonerId = prisonerId;
        this.medicalRestrictions = medicalRestrictions;
    }


    public boolean verifyDietRequirements() {
        return medicalRestrictions != null && !medicalRestrictions.isEmpty();
    }


    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }

    public String getPrisonerId() { return prisonerId; }
    public void setPrisonerId(String prisonerId) { this.prisonerId = prisonerId; }

    public String getMedicalRestrictions() { return medicalRestrictions; }
    public void setMedicalRestrictions(String medicalRestrictions) { this.medicalRestrictions = medicalRestrictions; }
}
