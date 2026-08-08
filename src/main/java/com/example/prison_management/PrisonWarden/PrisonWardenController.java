package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class PrisonWardenController {
    @javafx.fxml.FXML
    public void ProcessScheduledDischargeOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/ProcessScheduledDischarge.fxml", "ProcessScheduledDischarge" );

    }

    @javafx.fxml.FXML
    public void ViewInmateTrendsOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/ViewInmateTrends.fxml", "ViewInmateTrends" );

    }

    @javafx.fxml.FXML
    public void PendingFundingAllocationsOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PendingFundingAllocations.fxml", "PendingFundingAllocations" );

    }

    @javafx.fxml.FXML
    public void EmergencyLockdownOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/EmergencyLockdown.fxml", "EmergencyLockdown" );

    }

    @javafx.fxml.FXML
    public void PolicyManagementOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent , "/PrisonWarden/PolicyManagement.fxml" , "PolicyManagement" );
    }

    @javafx.fxml.FXML
    public void IssueStaffWarningOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent , "/PrisonWarden/IssueStaffWarning.fxml" , "IssueStaffWarning" );

    }

    @javafx.fxml.FXML
    public void LogOutOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent , "/Login.fxml" , "Login" );

    }

    @javafx.fxml.FXML
    public void AccountRevocationsOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent , "/PrisonWarden/AccountRevocations.fxml" , "AccountRevocations" );

    }

    @javafx.fxml.FXML
    public void LegalDischargeMandatesOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent , "/PrisonWarden/LegalDischargeMandates.fxml" , "LegalDischargeMandates" );

    }
}
