package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ViewInmateTrendsController {

    @FXML
    private ComboBox<String> ReportingTargetYearComboBox;

    @FXML
    private Label SummaryLabel;

    @FXML
    public void initialize() {
        ReportingTargetYearComboBox.getItems().addAll("2023", "2024", "2025", "2026");
    }

    @FXML
    public void LoadStatisticsOA(ActionEvent actionEvent) {
        String year = ReportingTargetYearComboBox.getValue();

        if (year == null) {
            SummaryLabel.setText("Please select a year.");
            return;
        }

        if (year.equals("2023")) {
            SummaryLabel.setText("Year: 2023\nAdmissions: 320\nDischarges: 280\nRecidivism: 45");
        } else if (year.equals("2024")) {
            SummaryLabel.setText("Year: 2024\nAdmissions: 350\nDischarges: 300\nRecidivism: 38");
        } else if (year.equals("2025")) {
            SummaryLabel.setText("Year: 2025\nAdmissions: 290\nDischarges: 310\nRecidivism: 25");
        } else if (year.equals("2026")) {
            SummaryLabel.setText("Year: 2026\nAdmissions: 240\nDischarges: 260\nRecidivism: 18");
        }
    }

    @FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "Prison Warden Dashboard");
    }
}