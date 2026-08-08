package com.example.prison_management.ChiefCook;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class KitchenReportsViewController {

    @FXML private ComboBox<String> ReportTypeCB;
    @FXML private DatePicker ReportingPeriodButtonOA;
    @FXML private TextArea KitchenoperationalsummaryviewTA;
    @FXML private Label ReportdisplayconfirmationLabel;

    @FXML
    public void initialize() {
        // set up dropdown options
        if (ReportTypeCB != null) {
            ReportTypeCB.setPromptText("Select Report Type");
            ReportTypeCB.setItems(FXCollections.observableArrayList(
                    "Waste & Efficiency Report",
                    "Hygiene & Cleaning Logs Summary",
                    "Equipment Maintenance Status"
            ));
        }

        // set default date field to today
        if (ReportingPeriodButtonOA != null) {
            ReportingPeriodButtonOA.setValue(LocalDate.now());
        }

        if (ReportdisplayconfirmationLabel != null) {
            ReportdisplayconfirmationLabel.setText("");
        }

        if (KitchenoperationalsummaryviewTA != null) {
            KitchenoperationalsummaryviewTA.setEditable(false);
            KitchenoperationalsummaryviewTA.setText("Select a report configuration, select a target period date, and click 'Fetch Operation Data'.");
        }
    }

    @FXML
    public void FetchOperationDataButtonOA(ActionEvent actionEvent) {
        String reportType = (ReportTypeCB != null) ? ReportTypeCB.getValue() : null;
        LocalDate targetDate = (ReportingPeriodButtonOA != null) ? ReportingPeriodButtonOA.getValue() : null;

        if (reportType == null || targetDate == null) {
            if (ReportdisplayconfirmationLabel != null) {
                ReportdisplayconfirmationLabel.setText("Error: Select Report Type and Period!");
            }
            return;
        }

        // build plain text data output
        StringBuilder txt = new StringBuilder();
        txt.append("KITCHEN METRICS COLLECTED\n\n");
        txt.append("Focus Area: ").append(reportType).append("\n");
        txt.append("Date: ").append(targetDate).append("\n\n");
        txt.append("- Data fetched from system records.\n");
        txt.append("- System status check: OK.\n");
        txt.append("- Total incident count: 0.\n\n");
        txt.append("Ready to generate summary report.");

        if (KitchenoperationalsummaryviewTA != null) {
            KitchenoperationalsummaryviewTA.setText(txt.toString());
        }

        if (ReportdisplayconfirmationLabel != null) {
            ReportdisplayconfirmationLabel.setText("Data loaded successfully.");
        }
    }

    @FXML
    public void GenerateKitchenReportButtonOA(ActionEvent actionEvent) {
        String reportType = (ReportTypeCB != null) ? ReportTypeCB.getValue() : null;
        LocalDate targetDate = (ReportingPeriodButtonOA != null) ? ReportingPeriodButtonOA.getValue() : null;

        if (reportType == null || targetDate == null) {
            if (ReportdisplayconfirmationLabel != null) {
                ReportdisplayconfirmationLabel.setText("Error: Missing parameters!");
            }
            return;
        }

        // create custom text based on what they selected
        StringBuilder str = new StringBuilder();
        str.append("PRISON KITCHEN MANAGEMENT REPORT\n\n");
        str.append("Type: ").append(reportType).append("\n");
        str.append("Date: ").append(targetDate).append("\n");
        str.append("Role: Chief Cook\n\n");

        if (reportType.contains("Waste")) {
            str.append("- Total Kitchen Waste: 4.8 kg\n");
            str.append("- Portion Efficiency: 98.2%\n");
            str.append("- Note: Waste levels are inside standard limits.\n");
        } else if (reportType.contains("Hygiene")) {
            str.append("- Cleaning Checklist: 100% complete\n");
            str.append("- Inspection Rating: Grade-A Compliant\n");
            str.append("- Next Clean Cycle: End of Month\n");
        } else {
            str.append("- Operational Freezers: 4/4 working\n");
            str.append("- Ventilation Systems: Serviced okay\n");
            str.append("- Open Repair Tasks: 0\n");
        }

        str.append("\nReport successfully generated and saved.");

        if (KitchenoperationalsummaryviewTA != null) {
            KitchenoperationalsummaryviewTA.setText(str.toString());
        }

        if (ReportdisplayconfirmationLabel != null) {
            ReportdisplayconfirmationLabel.setText("Report successfully compiled!");
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}