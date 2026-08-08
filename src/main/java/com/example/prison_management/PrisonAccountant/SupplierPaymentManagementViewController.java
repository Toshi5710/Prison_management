package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.mainuser.PayrollAndPayments;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SupplierPaymentManagementViewController {

    @FXML private TextField SupplierInvoiceIDTF;
    @FXML private TextField PaymentAmountTF;
    @FXML private Label PaymentReceiptConfirmationLabel;

    @FXML private TableView<PayrollAndPayments> SupplierPaymentManagementTV;
    @FXML private TableColumn<PayrollAndPayments, String> InvoiceIDTC;
    @FXML private TableColumn<PayrollAndPayments, String> SupplierNameTC;
    @FXML private TableColumn<PayrollAndPayments, Float> InvoiceAmountTC;
    @FXML private TableColumn<PayrollAndPayments, String> PaymentStatusTC;

    private final ObservableList<PayrollAndPayments> supplierPaymentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (SupplierInvoiceIDTF != null) SupplierInvoiceIDTF.setPromptText("Enter Invoice ID");
        if (PaymentAmountTF != null) PaymentAmountTF.setPromptText("e.g. 1500.00");
        if (PaymentReceiptConfirmationLabel != null) PaymentReceiptConfirmationLabel.setText("");

        // map table columns to model properties
        if (SupplierPaymentManagementTV != null) {
            InvoiceIDTC.setCellValueFactory(new PropertyValueFactory<>("recordId"));
            SupplierNameTC.setCellValueFactory(new PropertyValueFactory<>("entityName"));
            InvoiceAmountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
            PaymentStatusTC.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

            SupplierPaymentManagementTV.setItems(supplierPaymentList);
        }

        // sample supplier records
        supplierPaymentList.add(new PayrollAndPayments("INV-7001", "Acme Food Logistics", "SUPPLIER", 4500.00f, "PENDING"));
        supplierPaymentList.add(new PayrollAndPayments("INV-7002", "Global Security Hardware", "SUPPLIER", 8200.50f, "PENDING"));
        supplierPaymentList.add(new PayrollAndPayments("INV-7003", "Apex Medical Supplies", "SUPPLIER", 1350.00f, "PAID"));
    }

    @FXML
    public void FetchInvoiceButtonOA(ActionEvent actionEvent) {
        String inputID = (SupplierInvoiceIDTF != null) ? SupplierInvoiceIDTF.getText().trim() : "";

        if (inputID.isEmpty()) {
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Error: Enter an Invoice ID.");
            }
            return;
        }

        // standard manual loop search instead of streams
        PayrollAndPayments foundInvoice = null;
        for (PayrollAndPayments inv : supplierPaymentList) {
            if (inv.getRecordId().equalsIgnoreCase(inputID)) {
                foundInvoice = inv;
                break;
            }
        }

        if (foundInvoice != null) {
            if (SupplierPaymentManagementTV != null) {
                SupplierPaymentManagementTV.getSelectionModel().select(foundInvoice);
                SupplierPaymentManagementTV.scrollTo(foundInvoice);
            }
            if (PaymentAmountTF != null) {
                PaymentAmountTF.setText(String.valueOf(foundInvoice.getAmount()));
            }
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Invoice found for " + foundInvoice.getEntityName());
            }
        } else {
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("No matching invoice found.");
            }
        }
    }

    @FXML
    public void ProcessSupplierPaymentButtonOA(ActionEvent actionEvent) {
        PayrollAndPayments selectedInvoice = (SupplierPaymentManagementTV != null) ? SupplierPaymentManagementTV.getSelectionModel().getSelectedItem() : null;
        String amountText = (PaymentAmountTF != null) ? PaymentAmountTF.getText().trim() : "";

        if (selectedInvoice == null) {
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Error: Select an invoice row first.");
            }
            return;
        }

        if (amountText.isEmpty()) {
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Error: Payment amount field is empty.");
            }
            return;
        }

        try {
            float inputAmount = Float.parseFloat(amountText);

            if (selectedInvoice.getPaymentStatus().equalsIgnoreCase("PAID")) {
                if (PaymentReceiptConfirmationLabel != null) {
                    PaymentReceiptConfirmationLabel.setText("Error: This invoice is already paid.");
                }
                return;
            }

            // check if payment matches invoice cost closely
            if (Math.abs(inputAmount - selectedInvoice.getAmount()) > 0.01) {
                if (PaymentReceiptConfirmationLabel != null) {
                    PaymentReceiptConfirmationLabel.setText("Error: Amount must match total invoice value.");
                }
                return;
            }

            // update fields and refresh ui
            selectedInvoice.setPaymentStatus("PAID");

            if (SupplierPaymentManagementTV != null) SupplierPaymentManagementTV.refresh();

            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Payment completed for " + selectedInvoice.getRecordId());
            }

            if (SupplierInvoiceIDTF != null) SupplierInvoiceIDTF.clear();
            if (PaymentAmountTF != null) PaymentAmountTF.clear();

        } catch (NumberFormatException e) {
            if (PaymentReceiptConfirmationLabel != null) {
                PaymentReceiptConfirmationLabel.setText("Error: Please enter a valid number.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}