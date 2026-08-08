package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AppointmentSchedulerController
{
    @javafx.fxml.FXML
    private TextField enterPrisonerIDTextField;
    @javafx.fxml.FXML
    private Text fixAppointmentTDatePicker;
    @javafx.fxml.FXML
    private TableColumn inmateIDTableColumn;
    @javafx.fxml.FXML
    private ComboBox timeComboBox;
    @javafx.fxml.FXML
    private TableColumn severityCategoryTableColumn;
    @javafx.fxml.FXML
    private Label scheduleConfirmationLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalDashboard.fxml","Dashboard");
    }

    @javafx.fxml.FXML
    public void scheduleButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sickCallRequestsButtonOA(ActionEvent actionEvent) {
    }
}