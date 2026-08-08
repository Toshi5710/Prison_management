
package com.example.prison_management.mainuser;

import java.io.Serializable;

public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;

    private String budgetId;
    private float totalFunds;
    private float allocatedAmount;
    private String fiscalYear;

    public Budget() {}

    public Budget(String budgetId, float totalFunds, float allocatedAmount, String fiscalYear) {
        this.budgetId = budgetId;
        this.totalFunds = totalFunds;
        this.allocatedAmount = allocatedAmount;
        this.fiscalYear = fiscalYear;
    }


    public boolean verifyAvailableFunds(float requestedAmount) {
        return (totalFunds - allocatedAmount) >= requestedAmount;
    }


    public void updateBudgetAllocation(float amount) {
        this.allocatedAmount += amount;
    }


    public String getBudgetId() { return budgetId; }
    public void setBudgetId(String budgetId) { this.budgetId = budgetId; }

    public float getTotalFunds() { return totalFunds; }
    public void setTotalFunds(float totalFunds) { this.totalFunds = totalFunds; }

    public float getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(float allocatedAmount) { this.allocatedAmount = allocatedAmount; }

    public String getFiscalYear() { return fiscalYear; }
    public void setFiscalYear(String fiscalYear) { this.fiscalYear = fiscalYear; }
}
