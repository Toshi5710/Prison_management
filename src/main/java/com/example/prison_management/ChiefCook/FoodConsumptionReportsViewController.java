package com.example.prison_management.ChiefCook;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class FoodConsumptionReportsViewController {

    @FXML private DatePicker StartDateDP;
    @FXML private DatePicker EndDateDP;
    @FXML private ComboBox<String> CatagoryCB;
    @FXML private TextArea ConsumptionmetricsandlogsTA;
    @FXML private Label ReportgeneratednotificationLabel;

    @FXML
    public void initialize() {
        // load drop down values
        if (CatagoryCB != null) {
            CatagoryCB.setPromptText("Select Category");
            CatagoryCB.setItems(FXCollections.observableArrayList("Grains & Rice", "Lentils & Pulses", "Oils & Fats", "Vegetables"));
        }

        // set default dates
        if (StartDateDP != null) StartDateDP.setValue(LocalDate.now().minusDays(7));
        if (EndDateDP != null) EndDateDP.setValue(LocalDate.now());

        if (ReportgeneratednotificationLabel != null) {
            ReportgeneratednotificationLabel.setText("");
        }

        if (ConsumptionmetricsandlogsTA != null) {
            ConsumptionmetricsandlogsTA.setEditable(false);
            ConsumptionmetricsandlogsTA.setText("Select a category and date range, then click 'Fetch Consumption Records'.");
        }
    }

    @FXML
    public void FetchConsumptionRecordsButtonOA(ActionEvent actionEvent) {
        String category = CatagoryCB.getValue();
        LocalDate start = StartDateDP.getValue();
        LocalDate end = EndDateDP.getValue();

        if (category == null || start == null || end == null) {
            if (ReportgeneratednotificationLabel != null) {
                ReportgeneratednotificationLabel.setText("Error: Please select all fields!");
            }
            return;
        }

        if (start.isAfter(end)) {
            if (ReportgeneratednotificationLabel != null) {
                ReportgeneratednotificationLabel.setText("Error: Start Date cannot be after End Date!");
            }
            return;
        }

        // build up log records text without any fancy borders
        StringBuilder txt = new StringBuilder();
        txt.append("FOOD CONSUMPTION LOGS\n");
        txt.append("Category: ").append(category).append("\n");
        txt.append("Date Range: ").append(start).append(" to ").append(end).append("\n\n");
        txt.append("Log ").append(start).append(": Used 45 kg for breakfast and lunch.\n");
        txt.append("Log ").append(start.plusDays(1)).append(": Used 42 kg for meal distributions.\n");
        txt.append("Log ").append(end).append(": Used 48 kg.\n\n");
        txt.append("Done loading records.");

        if (ConsumptionmetricsandlogsTA != null) {
            ConsumptionmetricsandlogsTA.setText(txt.toString());
        }

        if (ReportgeneratednotificationLabel != null) {
            ReportgeneratednotificationLabel.setText("Fetched records for " + category);
        }
    }

    @FXML
    public void GenerateReportButtonOA(ActionEvent actionEvent) {
        String category = CatagoryCB.getValue();
        LocalDate start = StartDateDP.getValue();
        LocalDate end = EndDateDP.getValue();

        if (category == null || start == null || end == null) {
            if (ReportgeneratednotificationLabel != null) {
                ReportgeneratednotificationLabel.setText("Error: Fill filters first!");
            }
            return;
        }

        // create plain report summary text layout
        StringBuilder str = new StringBuilder();
        str.append("KITCHEN CONSUMPTION REPORT SUMMARY\n\n");
        str.append("Category Group: ").append(category).append("\n");
        str.append("Time Period: ").append(start).append(" to ").append(end).append("\n\n");
        str.append("Total Quantity Consumed: 135 kg\n");
        str.append("Average Daily Use: 45 kg/day\n");
        str.append("Wastage Rate: 1.2%\n\n");
        str.append("Report generated successfully.");

        if (ConsumptionmetricsandlogsTA != null) {
            ConsumptionmetricsandlogsTA.setText(str.toString());
        }

        if (ReportgeneratednotificationLabel != null) {
            ReportgeneratednotificationLabel.setText("Report successfully compiled!");
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}
