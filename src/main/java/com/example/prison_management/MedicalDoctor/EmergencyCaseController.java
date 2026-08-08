package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmergencyCaseController
{
    @javafx.fxml.FXML
    private TextField prisonerIDTextField;
    @javafx.fxml.FXML
    private TextField emergencyTextField;
    @javafx.fxml.FXML
    private Label emergencyStatusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","DashBoard");
    }

    @javafx.fxml.FXML
    public void generateEmergencyReportButtonOA(ActionEvent actionEvent) {
    }
}