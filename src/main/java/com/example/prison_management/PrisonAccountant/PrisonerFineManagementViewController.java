package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.mainuser.Fine;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrisonerFineManagementViewController {

    @FXML private TextField SearchFineTF;
    @FXML private TextField PaymentAmountTF;
    @FXML private Label UpdatedbalancedisplayLabel;

    @FXML private TableView<Fine> PrisonerFineManagementTV;
    @FXML private TableColumn<Fine, String> FineIDTC;
    @FXML private TableColumn<Fine, String> PrisonerIDTC;
    @FXML private TableColumn<Fine, Float> TotalFineTC;
    @FXML private TableColumn<Fine, Float> OutstandingBalanceTC;
    @FXML private TableColumn<Fine, String> StatusTC;

    private final ObservableList<Fine> fineList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (SearchFineTF != null) SearchFineTF.setPromptText("Enter Inmate/Fine ID");
        if (PaymentAmountTF != null) PaymentAmountTF.setPromptText("e.g. 500.00");
        if (UpdatedbalancedisplayLabel != null) UpdatedbalancedisplayLabel.setText("");

        // set up table columns
        if (PrisonerFineManagementTV != null) {
            FineIDTC.setCellValueFactory(new PropertyValueFactory<>("fineId"));
            PrisonerIDTC.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
            TotalFineTC.setCellValueFactory(new PropertyValueFactory<>("totalFine"));
            OutstandingBalanceTC.setCellValueFactory(new PropertyValueFactory<>("outstandingBalance"));
            StatusTC.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

            PrisonerFineManagementTV.setItems(fineList);
        }

        // sample fine data
        fineList.add(new Fine("F-901", "P-4402", 5000.0f, 3500.0f, "PARTIAL"));
        fineList.add(new Fine("F-902", "P-8819", 1200.0f, 1200.0f, "UNPAID"));
        fineList.add(new Fine("F-903", "P-2311", 2500.0f, 0.0f, "PAID"));
    }

    @FXML
    public void FetchFineDetailsButtonOA(ActionEvent actionEvent) {
        String query = (SearchFineTF != null) ? SearchFineTF.getText().trim() : "";

        if (query.isEmpty()) {
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Please enter a Fine ID or Prisoner ID.");
            }
            return;
        }

        // Student style manual linear search loop instead of stream api
        Fine foundFine = null;
        for (Fine f : fineList) {
            if (f.getFineId().equalsIgnoreCase(query) || f.getPrisonerId().equalsIgnoreCase(query)) {
                foundFine = f;
                break;
            }
        }

        if (foundFine != null) {
            if (PrisonerFineManagementTV != null) {
                PrisonerFineManagementTV.getSelectionModel().select(foundFine);
                PrisonerFineManagementTV.scrollTo(foundFine);
            }
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Record found. Balance: $" + foundFine.getOutstandingBalance());
            }
            if (PaymentAmountTF != null) {
                PaymentAmountTF.setText(String.valueOf(foundFine.getOutstandingBalance()));
            }
        } else {
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("No matching records found.");
            }
        }
    }

    @FXML
    public void UpdatePaymentStatusButtonOA(ActionEvent actionEvent) {
        Fine selectedFine = (PrisonerFineManagementTV != null) ? PrisonerFineManagementTV.getSelectionModel().getSelectedItem() : null;
        String amountText = (PaymentAmountTF != null) ? PaymentAmountTF.getText().trim() : "";

        if (selectedFine == null) {
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Error: Select a row from the table first.");
            }
            return;
        }

        if (amountText.isEmpty()) {
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Error: Enter a payment amount.");
            }
            return;
        }

        try {
            float paid = Float.parseFloat(amountText);

            if (paid <= 0) {
                if (UpdatedbalancedisplayLabel != null) {
                    UpdatedbalancedisplayLabel.setText("Error: Amount must be higher than zero.");
                }
                return;
            }

            // run validation checks
            if (!selectedFine.verifyOutstandingBalance()) {
                if (UpdatedbalancedisplayLabel != null) {
                    UpdatedbalancedisplayLabel.setText("Error: This fine is already fully paid.");
                }
                return;
            }

            if (paid > selectedFine.getOutstandingBalance()) {
                if (UpdatedbalancedisplayLabel != null) {
                    UpdatedbalancedisplayLabel.setText("Error: Payment cannot exceed outstanding balance.");
                }
                return;
            }

            // apply change via model method
            selectedFine.updateFinePaymentStatus(paid);

            // refresh UI component
            if (PrisonerFineManagementTV != null) PrisonerFineManagementTV.refresh();

            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Success! New Balance: $" + selectedFine.getOutstandingBalance() + " (" + selectedFine.getPaymentStatus() + ")");
            }

            if (PaymentAmountTF != null) PaymentAmountTF.clear();

        } catch (NumberFormatException e) {
            if (UpdatedbalancedisplayLabel != null) {
                UpdatedbalancedisplayLabel.setText("Error: Please enter a valid decimal number.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}