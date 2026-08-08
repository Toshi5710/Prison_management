package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HospitalTransferController
{
    @javafx.fxml.FXML
    private TextField transferInmateIDTextField;
    @javafx.fxml.FXML
    private TextField medicalNotesTextField;
    @javafx.fxml.FXML
    private ComboBox emergencyCategoryComboBox;
    @javafx.fxml.FXML
    private Label transferStatusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","DashBoard");
    }

    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {
    }
}