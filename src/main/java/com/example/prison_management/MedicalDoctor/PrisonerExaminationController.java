package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class PrisonerExaminationController
{
    @javafx.fxml.FXML
    private TextField prisonerNameTextField;
    @javafx.fxml.FXML
    private TableColumn idTableColumn;
    @javafx.fxml.FXML
    private TableColumn nameTableColumn;
    @javafx.fxml.FXML
    private Label examinationResultsLabel;
    @javafx.fxml.FXML
    private TextField prisonerIDTextField;
    @javafx.fxml.FXML
    private TableColumn medicineTableColumn;
    @javafx.fxml.FXML
    private TextField diagnosisTextField;
    @javafx.fxml.FXML
    private ComboBox medicineTimeComboBox;
    @javafx.fxml.FXML
    private TableColumn ageTableColumn;
    @javafx.fxml.FXML
    private TableColumn diagnosisTableColumn;
    @javafx.fxml.FXML
    private TextField ageTextField;
    @javafx.fxml.FXML
    private TableColumn medicineTimeTableColumn;
    @javafx.fxml.FXML
    private TextField medicineTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","DashBoard");
    }

    @javafx.fxml.FXML
    public void saveRecordButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void examinationStatusButtonOA(ActionEvent actionEvent) {
    }
}