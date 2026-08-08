package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.mainuser.Budget;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BudgetManagementController {

    @FXML private TextField AllocationAmountTF;
    @FXML private TableView<Budget> BudgetMAnagementTV;
    @FXML private TableColumn<Budget, String> FiscalYearTC;
    @FXML private TableColumn<Budget, Float> TotalBudgetTC;
    @FXML private TableColumn<Budget, Float> AllocatedAmountTC;
    @FXML private TableColumn<Budget, Float> RemainingBudgetTC;
    @FXML private ComboBox<String> CatagoryCB;
    @FXML private Label statusLabel;

    private final ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (AllocationAmountTF != null) AllocationAmountTF.setPromptText("Enter amount to allocate");
        if (statusLabel != null) statusLabel.setText("");

        // dropdown options
        if (CatagoryCB != null) {
            CatagoryCB.setPromptText("Select Category");
            CatagoryCB.setItems(FXCollections.observableArrayList("Food Operations", "Infrastructure Maintenance", "Healthcare Systems", "Staff Payroll"));
        }

        // map columns
        if (BudgetMAnagementTV != null) {
            FiscalYearTC.setCellValueFactory(new PropertyValueFactory<>("fiscalYear"));
            TotalBudgetTC.setCellValueFactory(new PropertyValueFactory<>("totalFunds"));
            AllocatedAmountTC.setCellValueFactory(new PropertyValueFactory<>("allocatedAmount"));
            RemainingBudgetTC.setCellValueFactory(new PropertyValueFactory<>("remainingBudget"));

            BudgetMAnagementTV.setItems(budgetList);
        }

        // initial sample rows
        budgetList.add(new Budget("B-2026-01", 500000.0f, 320000.0f, "FY 2026"));
        budgetList.add(new Budget("B-2027-02", 650000.0f, 150000.0f, "FY 2027"));
    }

    @FXML
    public void FetchBudgetRecordsButtonOA(ActionEvent actionEvent) {
        String cat = (CatagoryCB != null) ? CatagoryCB.getValue() : null;

        if (cat == null) {
            if (statusLabel != null) statusLabel.setText("Please select a category first.");
        } else {
            if (statusLabel != null) {
                statusLabel.setText("Loaded budget records for: " + cat);
            }
        }
    }

    @FXML
    public void UpdateAllocationButtonOA(ActionEvent actionEvent) {
        Budget selected = (BudgetMAnagementTV != null) ? BudgetMAnagementTV.getSelectionModel().getSelectedItem() : null;

        String txt = "";
        if (AllocationAmountTF != null && AllocationAmountTF.getText() != null) {
            txt = AllocationAmountTF.getText().trim();
        }

        if (selected == null) {
            if (statusLabel != null) statusLabel.setText("Error: Select a budget row first.");
            return;
        }

        if (txt.isEmpty()) {
            if (statusLabel != null) statusLabel.setText("Error: Allocation amount cannot be empty.");
            return;
        }

        try {
            float amt = Float.parseFloat(txt);

            if (amt <= 0) {
                if (statusLabel != null) statusLabel.setText("Error: Value must be higher than zero.");
                return;
            }

            // run verification check from model
            if (!selected.verifyAvailableFunds(amt)) {
                if (statusLabel != null) {
                    statusLabel.setText("Error: Not enough remaining funds.");
                }
                return;
            }

            // update data fields
            selected.updateBudgetAllocation(amt);

            // refresh view layout table
            if (BudgetMAnagementTV != null) BudgetMAnagementTV.refresh();

            if (statusLabel != null) {
                statusLabel.setText("Success: Allocated " + amt + " to " + selected.getFiscalYear());
            }
            if (AllocationAmountTF != null) AllocationAmountTF.clear();

        } catch (NumberFormatException e) {
            if (statusLabel != null) statusLabel.setText("Error: Please enter a valid decimal number.");
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}