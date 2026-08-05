package com.example.prison_management.PrisonWarden;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProcessScheduledDischargeController
{
    @javafx.fxml.FXML
    private TableView ProcessScheduledDischargeTV;
    @javafx.fxml.FXML
    private TextField InputTargetPrisonerIDTF;
    @javafx.fxml.FXML
    private TableColumn PrisonerNameTC;
    @javafx.fxml.FXML
    private TableColumn RemainingDaysTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void SearchAndLoadOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void DeleteRecordOA(ActionEvent actionEvent) {
    }
}