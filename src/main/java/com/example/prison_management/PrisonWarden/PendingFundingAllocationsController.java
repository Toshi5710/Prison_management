package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class PendingFundingAllocationsController {

    @FXML
    private TextField ProposalIDTF;
    @FXML
    private TableView<BudgetProposal> PendingFundingAllocationsTV;
    @FXML
    private TableColumn<BudgetProposal, String> PendingProposalIDTC;
    @FXML
    private TableColumn<BudgetProposal, Float> AmountTC;
    @FXML
    private TableColumn<BudgetProposal, String> FiscalYearTc;
    @FXML
    private TableColumn<BudgetProposal, String> NarrativeTC;
    @FXML
    private TableColumn<BudgetProposal, String> StatusTC;
    @FXML
    private ComboBox<String> DecisionComboBox;

    @FXML
    public void initialize() {

        PendingProposalIDTC.setCellValueFactory(new PropertyValueFactory<>("proposalId"));
        AmountTC.setCellValueFactory(new PropertyValueFactory<>("proposedAmount"));
        FiscalYearTc.setCellValueFactory(new PropertyValueFactory<>("targetFiscalYear"));
        NarrativeTC.setCellValueFactory(new PropertyValueFactory<>("justificationNarrative"));
        StatusTC.setCellValueFactory(new PropertyValueFactory<>("verificationStatus"));

        DecisionComboBox.getItems().addAll("Approved", "Rejected", "Hold");

        PendingFundingAllocationsTV.getItems().clear();
        File f = new File("BudgetProposal.bin");
        if (f.exists() && f.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    try {
                        BudgetProposal prop = (BudgetProposal) ois.readObject();
                        PendingFundingAllocationsTV.getItems().add(prop);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void SubmitDecisionOA(ActionEvent actionEvent) {
        String targetId = ProposalIDTF.getText().trim();
        String selectedDecision = DecisionComboBox.getValue();

        if (targetId.isEmpty() || selectedDecision == null) {
            Helper.showErrorAlert("Please enter a Proposal ID and select a Decision!");
            return;
        }

        File f = new File("BudgetProposal.bin");
        if (!f.exists() || f.length() == 0) {
            Helper.showErrorAlert("No proposals found!");
            return;
        }

        ArrayList<BudgetProposal> list = new ArrayList<>();
        boolean found = false;

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    BudgetProposal prop = (BudgetProposal) ois.readObject();
                    if (prop.getProposalId().equalsIgnoreCase(targetId)) {
                        found = true;
                        prop.setVerificationStatus(selectedDecision);
                    }
                    list.add(prop);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!found) {
            Helper.showErrorAlert("Proposal ID not found!");
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (BudgetProposal prop : list) {
                oos.writeObject(prop);
            }
            oos.close();

            Helper.showSuccessAlert("Success", "Decision submitted");

            ProposalIDTF.clear();
            DecisionComboBox.setValue(null);

            PendingFundingAllocationsTV.getItems().clear();
            PendingFundingAllocationsTV.getItems().addAll(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "Prison Warden Dashboard");
    }
}