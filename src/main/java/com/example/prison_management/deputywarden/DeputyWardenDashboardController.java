package com.example.prison_management.deputywarden;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class DeputyWardenDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void inspectionScheduleButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/InspectionSchedule.fxml",
                "Inspection Schedule"
        );
    }

    @javafx.fxml.FXML
    public void capacityMonitoringButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/CapacityMonitoring.fxml",
                "Capacity Monitoring"
        );
    }

    @javafx.fxml.FXML
    public void assignDutiesButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/AssignDuty.fxml",
                "Assign Duties"
        );
    }

    @javafx.fxml.FXML
    public void visitorRecordsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/VisitorRecords.fxml",
                "Visitor Records"
        );
    }

    @javafx.fxml.FXML
    public void incidentReportsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/IncidentReport.fxml",
                "Incident Report"
        );
    }

    @javafx.fxml.FXML
    public void staffRecruitmentButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/StaffRecruitment.fxml",
                "Staff Recruitment"
        );
    }

    @javafx.fxml.FXML
    public void transferRequestButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/TransferRequest.fxml",
                "Transfer Request"
        );
    }

    @javafx.fxml.FXML
    public void logOutButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/com/example/prison_management/Login.fxml",
                "Login"
        );
    }

    @javafx.fxml.FXML
    public void dailyActivitiesButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/deputywarden/DailyActivities.fxml",
                "Daily Activities"
        );
    }
}