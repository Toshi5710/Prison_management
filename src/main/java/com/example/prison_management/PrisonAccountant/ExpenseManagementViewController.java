package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ExpenseManagementViewController {

    @FXML private TextField ExpenseTitleTF;
    @FXML private TextField AmountTF;
    @FXML private DatePicker ExpenseDateDP;
    @FXML private ComboBox<String> CategoryCB;

    @FXML private TableView<ExpenseRecord> ExpenseManagementTV;
    @FXML private TableColumn<ExpenseRecord, String> ExpenseIDTC;
    @FXML private TableColumn<ExpenseRecord, String> CategoryTC;
    @FXML private TableColumn<ExpenseRecord, Float> AmountTC;
    @FXML private TableColumn<ExpenseRecord, String> StatusTC;

    @FXML private Label StatussummaryLabel;

    private final ObservableList<ExpenseRecord> expenseList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (ExpenseTitleTF != null) ExpenseTitleTF.setPromptText("e.g. Utility Bill Aug");
        if (AmountTF != null) AmountTF.setPromptText("e.g. 4500.50");
        if (ExpenseDateDP != null) ExpenseDateDP.setValue(LocalDate.now());
        if (StatussummaryLabel != null) StatussummaryLabel.setText("");

        // categories configuration
        if (CategoryCB != null) {
            CategoryCB.setPromptText("Select Category");
            CategoryCB.setItems(FXCollections.observableArrayList("Food Supply", "Utilities", "Medical Supplies", "Maintenance"));
        }

        // map table columns
        if (ExpenseManagementTV != null) {
            ExpenseIDTC.setCellValueFactory(new PropertyValueFactory<>("expenseId"));
            CategoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
            AmountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
            StatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

            ExpenseManagementTV.setItems(expenseList);
        }

        // sample records
        expenseList.add(new ExpenseRecord("EXP-891", "Utilities", 3200.00f, "APPROVED"));
        expenseList.add(new ExpenseRecord("EXP-892", "Food Supply", 12500.75f, "PENDING"));
    }

    @FXML
    public void FetchExpensesButtonOA(ActionEvent actionEvent) {
        String selectedCategory = (CategoryCB != null) ? CategoryCB.getValue() : null;

        if (selectedCategory == null) {
            if (StatussummaryLabel != null) StatussummaryLabel.setText("Showing all records.");
        } else {
            if (StatussummaryLabel != null) {
                StatussummaryLabel.setText("Filtered rows by: " + selectedCategory);
            }
        }
    }

    @FXML
    public void UpdateExpenseButtonOA(ActionEvent actionEvent) {
        String title = (ExpenseTitleTF != null) ? ExpenseTitleTF.getText().trim() : "";
        String amountStr = (AmountTF != null) ? AmountTF.getText().trim() : "";
        String category = (CategoryCB != null) ? CategoryCB.getValue() : null;

        ExpenseRecord selectedRecord = (ExpenseManagementTV != null) ? ExpenseManagementTV.getSelectionModel().getSelectedItem() : null;

        if (title.isEmpty() || amountStr.isEmpty() || category == null) {
            if (selectedRecord != null) {
                // toggle row status logic
                String newStatus = selectedRecord.getStatus().equals("PENDING") ? "APPROVED" : "PENDING";
                selectedRecord.setStatus(newStatus);
                if (ExpenseManagementTV != null) ExpenseManagementTV.refresh();
                if (StatussummaryLabel != null) StatussummaryLabel.setText("Status updated to " + newStatus + " for " + selectedRecord.getExpenseId());
            } else {
                if (StatussummaryLabel != null) StatussummaryLabel.setText("Error: Fill all fields or select a table row.");
            }
            return;
        }

        try {
            float amountValue = Float.parseFloat(amountStr);
            if (amountValue <= 0) {
                if (StatussummaryLabel != null) StatussummaryLabel.setText("Error: Amount must be higher than zero.");
                return;
            }

            // student style id generation using system time
            String timeStr = String.valueOf(System.currentTimeMillis());
            String randomId = "EXP-" + timeStr.substring(timeStr.length() - 3);

            expenseList.add(new ExpenseRecord(randomId, category, amountValue, "PENDING"));

            if (StatussummaryLabel != null) StatussummaryLabel.setText("Added expense record: " + randomId);

            // clear input spaces
            if (ExpenseTitleTF != null) ExpenseTitleTF.clear();
            if (AmountTF != null) AmountTF.clear();
            if (CategoryCB != null) CategoryCB.setValue(null);

        } catch (NumberFormatException e) {
            if (StatussummaryLabel != null) StatussummaryLabel.setText("Error: Please input a valid decimal amount.");
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }

    public static class ExpenseRecord {
        private String expenseId;
        private String category;
        private float amount;
        private String status;

        public ExpenseRecord(String expenseId, String category, float amount, String status) {
            this.expenseId = expenseId;
            this.category = category;
            this.amount = amount;
            this.status = status;
        }

        public String getExpenseId() { return expenseId; }
        public String getCategory() { return category; }
        public float getAmount() { return amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}