package com.example.prison_management.PrisonWarden;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PendingFundingAllocationsController
{
    @javafx.fxml.FXML
    private TextField ProposalIDTF;
    @javafx.fxml.FXML
    private TableView PendingFundingAllocationsTV;
    @javafx.fxml.FXML
    private TableColumn PendingProposalIDTC;
    @javafx.fxml.FXML
    private ComboBox DecisionComboBox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void SubmitDecisionOA(ActionEvent actionEvent) {
    }
}