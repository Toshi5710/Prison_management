package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MedicalHistoryController
{
    @javafx.fxml.FXML
    private TextField enterPrisonerIDTextField;
    @javafx.fxml.FXML
    private TableColumn treatmentTableColumn;
    @javafx.fxml.FXML
    private TableColumn dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn diagnosisTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void fetchHistoryButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","DashBoard");
    }
}