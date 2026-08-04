package com.example.prison_management.MedicalDoctor;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class PrescriptionController
{
    @javafx.fxml.FXML
    private TableColumn prisonerNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn prisonerIDTableColumn;
    @javafx.fxml.FXML
    private Label PrescriptionStatusLabel;
    @javafx.fxml.FXML
    private TextField medicineDosageTextField;
    @javafx.fxml.FXML
    private TableColumn medicineRequestsTableColumn;
    @javafx.fxml.FXML
    private TableColumn diagnosisTableColumn;
    @javafx.fxml.FXML
    private TextField medicineNameTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showRefillRequestsButtonOA(ActionEvent actionEvent) {
    }
}