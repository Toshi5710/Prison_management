package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class ChiefSecurityGuardDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void prisonerHeadCountButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefSecurityGuard/PrisonerHeadCount.fxml","Head Count");
    }

    @javafx.fxml.FXML
    public void leaveRequestsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ShiftLeaveRequests.fxml","Leave Requests");

    }

    @javafx.fxml.FXML
    public void threatLevelButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ThreatLevelManagement.fxml","Threat Level");
    }

    @javafx.fxml.FXML
    public void gateSecurityButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/CargoGateRegistry.fxml"," Gate Entry");
    }

    @javafx.fxml.FXML
    public void visitorScreeningButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/VisitorScreening.fxml","Visitor Screening");
    }

    @javafx.fxml.FXML
    public void cellInspectionsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/CellInspection.fxml","Cell Inspection Report");
    }

    @javafx.fxml.FXML
    public void securityTransferButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/SecurityTransferRequest.fxml","SecurityTransfer");
    }

    @javafx.fxml.FXML
    public void logOutButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(actionEvent,"/com/example/prison_management/Login.fxml","Login");
    }

    @javafx.fxml.FXML
    public void surveillanceButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/SecuritySurveillance.fxml","Security Camera");

    }

}