package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionMonitoringViewController {

    @FXML private ComboBox<String> FilterbyAccountCB;
    @FXML private Label TransactionSummaryLabel;

    @FXML private TableView<Transaction> TransactionMonitoringTV;
    @FXML private TableColumn<Transaction, String> TransactionIDTC;
    @FXML private TableColumn<Transaction, String> AccountTC;
    @FXML private TableColumn<Transaction, Float> AmountTC;
    @FXML private TableColumn<Transaction, String> StatusTC;

    private final ObservableList<Transaction> masterTransactionList = FXCollections.observableArrayList();

    // inner class for table data tracking
    public static class Transaction {
        private String transactionId;
        private String account;
        private float amount;
        private String status;

        public Transaction(String transactionId, String account, float amount, String status) {
            this.transactionId = transactionId;
            this.account = account;
            this.amount = amount;
            this.status = status;
        }

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

        public String getAccount() { return account; }
        public void setAccount(String account) { this.account = account; }

        public float getAmount() { return amount; }
        public void setAmount(float amount) { this.amount = amount; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    @FXML
    public void initialize() {
        if (TransactionSummaryLabel != null) {
            TransactionSummaryLabel.setText("Select filters and fetch transaction logs.");
        }

        // drop down account filters
        if (FilterbyAccountCB != null) {
            FilterbyAccountCB.setItems(FXCollections.observableArrayList(
                    "All Accounts",
                    "Inmate Trust Fund",
                    "Facility Operations",
                    "Asset Procurement",
                    "Revenue & Fines"
            ));
            FilterbyAccountCB.setValue("All Accounts");
        }

        // map tableview columns
        if (TransactionMonitoringTV != null) {
            TransactionIDTC.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
            AccountTC.setCellValueFactory(new PropertyValueFactory<>("account"));
            AmountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
            StatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        }

        // sample transactions data
        masterTransactionList.add(new Transaction("TXN-4001", "Inmate Trust Fund", 120.50f, "PENDING"));
        masterTransactionList.add(new Transaction("TXN-4002", "Facility Operations", 4500.00f, "VERIFIED"));
        masterTransactionList.add(new Transaction("TXN-4003", "Asset Procurement", 850.00f, "PENDING"));
        masterTransactionList.add(new Transaction("TXN-4004", "Revenue & Fines", 350.00f, "VERIFIED"));
        masterTransactionList.add(new Transaction("TXN-4005", "Inmate Trust Fund", 65.00f, "PENDING"));
    }

    @FXML
    public void FetchTransactionsButtonOA(ActionEvent actionEvent) {
        String selectedAccount = (FilterbyAccountCB != null) ? FilterbyAccountCB.getValue() : "All Accounts";

        if (selectedAccount == null || selectedAccount.equals("All Accounts")) {
            if (TransactionMonitoringTV != null) {
                TransactionMonitoringTV.setItems(masterTransactionList);
            }
            updateSummaryLabel(masterTransactionList);
        } else {
            // standard student-style manual filter loop instead of predicate streaming
            ObservableList<Transaction> filteredList = FXCollections.observableArrayList();
            for (Transaction txn : masterTransactionList) {
                if (txn.getAccount().equalsIgnoreCase(selectedAccount)) {
                    filteredList.add(txn);
                }
            }

            if (TransactionMonitoringTV != null) {
                TransactionMonitoringTV.setItems(filteredList);
            }
            updateSummaryLabel(filteredList);
        }
    }

    @FXML
    public void VerifyAndUpdateStatusButtonOA(ActionEvent actionEvent) {
        Transaction selectedTxn = (TransactionMonitoringTV != null) ? TransactionMonitoringTV.getSelectionModel().getSelectedItem() : null;

        if (selectedTxn == null) {
            if (TransactionSummaryLabel != null) {
                TransactionSummaryLabel.setText("Error: Select a row from the table first.");
            }
            return;
        }

        if (selectedTxn.getStatus().equalsIgnoreCase("VERIFIED")) {
            if (TransactionSummaryLabel != null) {
                TransactionSummaryLabel.setText("Notice: " + selectedTxn.getTransactionId() + " is already verified.");
            }
            return;
        }

        // change status and refresh table view
        selectedTxn.setStatus("VERIFIED");

        if (TransactionMonitoringTV != null) {
            TransactionMonitoringTV.refresh();
            updateSummaryLabel((ObservableList<Transaction>) TransactionMonitoringTV.getItems());
        }

        if (TransactionSummaryLabel != null) {
            TransactionSummaryLabel.setText("Success: Verified transaction " + selectedTxn.getTransactionId());
        }
    }

    private void updateSummaryLabel(ObservableList<Transaction> activeList) {
        if (TransactionSummaryLabel == null) return;

        int totalCount = activeList.size();
        float totalValue = 0;
        int pendingCount = 0;

        for (Transaction t : activeList) {
            totalValue += t.getAmount();
            if (t.getStatus().equalsIgnoreCase("PENDING")) {
                pendingCount++;
            }
        }

        TransactionSummaryLabel.setText(String.format("Showing %d entries | Total Value: $%.2f | Pending: %d",
                totalCount, totalValue, pendingCount));
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}