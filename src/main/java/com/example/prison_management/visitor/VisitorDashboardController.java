package com.example.prison_management.visitor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class VisitorDashboardController {

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void requestAppointmentButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/VisitorAppointment.fxml",
                "Request Appointment"
        );
    }

    @javafx.fxml.FXML
    public void appointmentStatusButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/AppointmentStatus.fxml",
                "Appointment Status"
        );
    }

    @javafx.fxml.FXML
    public void cancelAppointmentButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/CancelAppointment.fxml",
                "Cancel Appointment"
        );
    }

    @javafx.fxml.FXML
    public void emergencyVisitButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/EmergencyVisit.fxml",
                "Emergency Visit"
        );
    }

    @javafx.fxml.FXML
    public void visitRulesButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/VisitRules.fxml",
                "Visit Rules"
        );
    }

    @javafx.fxml.FXML
    public void identifyVerificationButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/IdentityVerification.fxml",
                "Identity Verification"
        );
    }

    @javafx.fxml.FXML
    public void feedbackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/Feedback.fxml",
                "Feedback"
        );
    }

    @javafx.fxml.FXML
    public void notificationButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/Notification.fxml",
                "Notifications"
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
}