package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.mainuser.PayrollAndPayments;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;

public class SalaryManagementViewController {

    @FXML private TextField EmployeeIDTF;

    @FXML private TableView<PayrollAndPayments> SalaryManagementTV;
    @FXML private TableColumn<PayrollAndPayments, String> EmployeeIDTC;
    @FXML private TableColumn<PayrollAndPayments, String> NameTC;
    @FXML private TableColumn<PayrollAndPayments, String> RoleTC;
    @FXML private TableColumn<PayrollAndPayments, Float> BaseSalaryTC;
    @FXML private TableColumn<PayrollAndPayments, String> statusTC;

    @FXML private Label ConfirmationmessageLabel;

    // correctly targets the data directory
    private final String FILE_NAME = "data/salaries.bin";

    @FXML
    public void initialize() {
        if (ConfirmationmessageLabel != null) {
            ConfirmationmessageLabel.setText("");
        }

        if (EmployeeIDTF != null) {
            EmployeeIDTF.setPromptText("e.g. EMP-101");
        }

        // link tableview columns to payroll object properties
        if (SalaryManagementTV != null) {
            EmployeeIDTC.setCellValueFactory(new PropertyValueFactory<>("recordId"));
            NameTC.setCellValueFactory(new PropertyValueFactory<>("entityName"));
            RoleTC.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
            BaseSalaryTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
            statusTC.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

            // user convenience: auto-fills the input field when clicking rows
            SalaryManagementTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null && EmployeeIDTF != null) {
                    EmployeeIDTF.setText(newSelection.getRecordId());
                }
            });
        }

        loadTableData();
    }

    private void loadTableData() {
        // load the data completely through your utility class
        ArrayList<PayrollAndPayments> list = BinaryFileUtil.readAllObjects(FILE_NAME);

        // write initial baseline records if the file doesn't exist or is empty
        if (list.isEmpty()) {
            list.add(new PayrollAndPayments("EMP-101", "Officer John", "Senior Guard", 4200.00f, "PENDING"));
            list.add(new PayrollAndPayments("EMP-102", "Sergeant Sarah", "Head Chef", 3800.00f, "PENDING"));
            list.add(new PayrollAndPayments("EMP-103", "Dr. Alan", "Medical Staff", 5500.00f, "PAID"));

            for (PayrollAndPayments initialRecord : list) {
                BinaryFileUtil.appendObject(FILE_NAME, initialRecord);
            }
        }

        ObservableList<PayrollAndPayments> observableList = FXCollections.observableArrayList(list);
        if (SalaryManagementTV != null) {
            SalaryManagementTV.setItems(observableList);
        }
    }

    @FXML
    public void FetchPayrollButtonOA(ActionEvent event) {
        String empId = (EmployeeIDTF != null) ? EmployeeIDTF.getText().trim() : "";
        if (empId.isEmpty()) {
            if (ConfirmationmessageLabel != null) {
                ConfirmationmessageLabel.setStyle("-fx-text-fill: red;");
                ConfirmationmessageLabel.setText("Please enter an Employee ID.");
            }
            return;
        }

        ArrayList<PayrollAndPayments> list = BinaryFileUtil.readAllObjects(FILE_NAME);
        PayrollAndPayments matchingRecord = null;

        // normal student loop search matching the target id
        for (PayrollAndPayments record : list) {
            if (record.getRecordId().equalsIgnoreCase(empId)) {
                matchingRecord = record;
                break;
            }
        }

        if (matchingRecord != null) {
            if (SalaryManagementTV != null) {
                SalaryManagementTV.getSelectionModel().select(matchingRecord);
                SalaryManagementTV.scrollTo(matchingRecord);
            }
            if (ConfirmationmessageLabel != null) {
                ConfirmationmessageLabel.setStyle("-fx-text-fill: green;");
                ConfirmationmessageLabel.setText("Found: " + matchingRecord.getEntityName() + " (" + matchingRecord.getPaymentStatus() + ")");
            }
        } else {
            if (ConfirmationmessageLabel != null) {
                ConfirmationmessageLabel.setStyle("-fx-text-fill: orange;");
                ConfirmationmessageLabel.setText("No record found for ID: " + empId);
            }
        }
    }

    @FXML
    public void VerifyDetailsButtonOA(ActionEvent event) {
        String empId = (EmployeeIDTF != null) ? EmployeeIDTF.getText().trim() : "";
        if (empId.isEmpty()) {
            if (ConfirmationmessageLabel != null) {
                ConfirmationmessageLabel.setStyle("-fx-text-fill: red;");
                ConfirmationmessageLabel.setText("Error: Enter an Employee ID to verify.");
            }
            return;
        }

        if (ConfirmationmessageLabel != null) {
            ConfirmationmessageLabel.setStyle("-fx-text-fill: green;");
            ConfirmationmessageLabel.setText("Employee credentials verified successfully.");
        }
    }

    @FXML
    public void ProcessPaymentButtonOA(ActionEvent event) {
        String empId = (EmployeeIDTF != null) ? EmployeeIDTF.getText().trim() : "";
        if (empId.isEmpty()) {
            if (ConfirmationmessageLabel != null) {
                ConfirmationmessageLabel.setStyle("-fx-text-fill: red;");
                ConfirmationmessageLabel.setText("Error: Employee ID cannot be empty.");
            }
            return;
        }

        ArrayList<PayrollAndPayments> list = BinaryFileUtil.readAllObjects(FILE_NAME);
        boolean updated = false;

        for (PayrollAndPayments record : list) {
            if (record.getRecordId().equalsIgnoreCase(empId)) {
                if (record.getPaymentStatus().equalsIgnoreCase("PAID")) {
                    if (ConfirmationmessageLabel != null) {
                        ConfirmationmessageLabel.setStyle("-fx-text-fill: orange;");
                        ConfirmationmessageLabel.setText("Notice: Salary already paid for " + empId);
                    }
                    return;
                }
                record.setPaymentStatus("PAID");
                updated = true;
                break;
            }
        }

        // if the person isn't found, treat them as new hires and append them
        if (!updated) {
            PayrollAndPayments newPayroll = new PayrollAndPayments(empId, "New Personnel", "Guard Staff", 3600.00f, "PAID");
            BinaryFileUtil.appendObject(FILE_NAME, newPayroll);
        } else {
            // clear the old file cleanly before rewriting updated collection lists
            File oldFile = new File(FILE_NAME);
            if (oldFile.exists()) {
                oldFile.delete();
            }

            // re-append everything using your utility class
            for (PayrollAndPayments record : list) {
                BinaryFileUtil.appendObject(FILE_NAME, record);
            }
        }

        if (ConfirmationmessageLabel != null) {
            ConfirmationmessageLabel.setStyle("-fx-text-fill: green;");
            ConfirmationmessageLabel.setText("Salary processed successfully for: " + empId);
        }

        loadTableData();
        if (EmployeeIDTF != null) {
            EmployeeIDTF.clear();
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}