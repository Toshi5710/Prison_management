package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HealthReportGeneratorController
{
    @javafx.fxml.FXML
    private TextField inmateIDTextField;
    @javafx.fxml.FXML
    private Label reportSummaryLabel;
    @javafx.fxml.FXML
    private DatePicker reportingPeriodDatePicker;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","DashBoard");
    }

    @javafx.fxml.FXML
    public void generateHealthReportButtonOA(ActionEvent actionEvent) {
    }
}