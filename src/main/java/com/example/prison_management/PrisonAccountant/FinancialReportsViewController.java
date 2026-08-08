package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class FinancialReportsViewController {

    @FXML private ComboBox<String> ReportTypeCB;
    @FXML private DatePicker StartDateDP;
    @FXML private DatePicker EndDateDP;
    @FXML private TextArea FinancialReportTA;
    @FXML private Label ReportgenerationstatusLabel;

    @FXML
    public void initialize() {
        // drop down items
        if (ReportTypeCB != null) {
            ReportTypeCB.setPromptText("Select Report Type");
            ReportTypeCB.setItems(FXCollections.observableArrayList(
                    "Quarterly Budget Statement",
                    "Facility Expense Audit",
                    "Inmate Trust Fund Summary",
                    "Revenue & Fine Collection Log"
            ));
        }

        // default date range
        if (StartDateDP != null) StartDateDP.setValue(LocalDate.now().minusMonths(1));
        if (EndDateDP != null) EndDateDP.setValue(LocalDate.now());

        if (ReportgenerationstatusLabel != null) {
            ReportgenerationstatusLabel.setText("");
        }
        if (FinancialReportTA != null) {
            FinancialReportTA.setEditable(false);
            FinancialReportTA.setText("Select report configurations above, then press 'Fetch Data'.");
        }
    }

    @FXML
    public void FetchDataButtonOA(ActionEvent actionEvent) {
        String type = (ReportTypeCB != null) ? ReportTypeCB.getValue() : null;
        LocalDate start = (StartDateDP != null) ? StartDateDP.getValue() : null;
        LocalDate end = (EndDateDP != null) ? EndDateDP.getValue() : null;

        if (type == null || start == null || end == null) {
            if (ReportgenerationstatusLabel != null) {
                ReportgenerationstatusLabel.setText("Error: Missing fields to load data.");
            }
            return;
        }

        if (start.isAfter(end)) {
            if (ReportgenerationstatusLabel != null) {
                ReportgenerationstatusLabel.setText("Error: Start Date cannot be after End Date.");
            }
            return;
        }

        // basic mock data text
        StringBuilder dataDump = new StringBuilder();
        dataDump.append("Data Loaded Successfully\n");
        dataDump.append("Report Type: ").append(type).append("\n");
        dataDump.append("Range: ").append(start).append(" to ").append(end).append("\n\n");
        dataDump.append("- Found matching transaction records.\n");
        dataDump.append("- Balances match with primary ledger.\n");
        dataDump.append("Click 'Generate Report' to view official document summary.");

        if (FinancialReportTA != null) {
            FinancialReportTA.setText(dataDump.toString());
        }
        if (ReportgenerationstatusLabel != null) {
            ReportgenerationstatusLabel.setText("Data successfully loaded from records.");
        }
    }

    @FXML
    public void GenerateReportButtonOA(ActionEvent actionEvent) {
        String type = (ReportTypeCB != null) ? ReportTypeCB.getValue() : null;
        LocalDate start = (StartDateDP != null) ? StartDateDP.getValue() : null;
        LocalDate end = (EndDateDP != null) ? EndDateDP.getValue() : null;

        if (type == null || start == null || end == null) {
            if (ReportgenerationstatusLabel != null) {
                ReportgenerationstatusLabel.setText("Error: Cannot build report. Check fields.");
            }
            return;
        }

        // standard basic report layout
        StringBuilder officialReport = new StringBuilder();
        officialReport.append("PRISON FINANCIAL AUDIT REPORT\n");
        officialReport.append("-----------------------------\n");
        officialReport.append("Type: ").append(type.toUpperCase()).append("\n");
        officialReport.append("Period: ").append(start).append(" to ").append(end).append("\n");
        officialReport.append("Prepared By: Prison Accountant\n");
        officialReport.append("-----------------------------\n");

        if (type.contains("Budget")) {
            officialReport.append("- Total Budget: $500,000.00\n");
            officialReport.append("- Expenses: $320,000.00\n");
            officialReport.append("- Remaining: $180,000.00\n");
        } else if (type.contains("Expense")) {
            officialReport.append("- Utilities: $14,250.00\n");
            officialReport.append("- Kitchen Costs: $35,800.00\n");
            officialReport.append("- Maintenance: $1,200.00\n");
        } else if (type.contains("Trust")) {
            officialReport.append("- Monitored Accounts: 450\n");
            officialReport.append("- Total Deposits: $12,450.00\n");
            officialReport.append("- Total Withdrawals: $9,120.00\n");
        } else {
            officialReport.append("- Open Fine Cases: 24\n");
            officialReport.append("- Fines Collected: $4,500.00\n");
            officialReport.append("- Remaining Balances: $1,850.00\n");
        }

        officialReport.append("-----------------------------\n");
        officialReport.append("Status: Finalized and logged.");

        if (FinancialReportTA != null) {
            FinancialReportTA.setText(officialReport.toString());
        }
        if (ReportgenerationstatusLabel != null) {
            ReportgenerationstatusLabel.setText("Report generated successfully.");
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}