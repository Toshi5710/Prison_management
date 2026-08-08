package com.example.prison_management.ChiefCook;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MealDistributionViewController {

    @FXML private TextField WardTF;
    @FXML private TextField ServingsDeliveredTF;
    @FXML private TextField TimeTF;
    @FXML private Label DistributionStatusLabel;

    @FXML private TableView<DistributionRecord> MealDistributionTV;
    @FXML private TableColumn<DistributionRecord, String> WardTC;
    @FXML private TableColumn<DistributionRecord, Integer> AllocatedMealsTC;
    @FXML private TableColumn<DistributionRecord, Integer> DistributedMealsTC;
    @FXML private TableColumn<DistributionRecord, String> StatusTC;

    private final ObservableList<DistributionRecord> distributionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (WardTF != null) WardTF.setPromptText("e.g. Ward-A");
        if (TimeTF != null) TimeTF.setPromptText("e.g. 08:30 AM");
        if (ServingsDeliveredTF != null) ServingsDeliveredTF.setPromptText("e.g. 120");
        if (DistributionStatusLabel != null) DistributionStatusLabel.setText("");

        // set up table columns
        if (MealDistributionTV != null) {
            WardTC.setCellValueFactory(new PropertyValueFactory<>("wardName"));
            AllocatedMealsTC.setCellValueFactory(new PropertyValueFactory<>("allocatedCount"));
            DistributedMealsTC.setCellValueFactory(new PropertyValueFactory<>("distributedCount"));
            StatusTC.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));

            MealDistributionTV.setItems(distributionList);
        }

        // sample starting rows
        distributionList.add(new DistributionRecord("Ward-A", 120, 120, "COMPLETED"));
        distributionList.add(new DistributionRecord("Ward-B", 85, 80, "SHORTAGE"));
    }

    @FXML
    public void FetchMealAllocationsButtonOA(ActionEvent actionEvent) {
        String w = (WardTF != null) ? WardTF.getText().trim() : "";
        if (w.isEmpty()) {
            if (DistributionStatusLabel != null) {
                DistributionStatusLabel.setText("Please enter a Ward ID first!");
            }
            return;
        }

        if (DistributionStatusLabel != null) {
            DistributionStatusLabel.setText("Allocations loaded for " + w);
        }
        if (ServingsDeliveredTF != null) {
            ServingsDeliveredTF.setText("100");
        }
        if (TimeTF != null) {
            TimeTF.setText("12:30 PM");
        }
    }

    @FXML
    public void SaveDistributionRecordButtonOA(ActionEvent actionEvent) {
        String w = (WardTF != null) ? WardTF.getText().trim() : "";
        String qty = (ServingsDeliveredTF != null) ? ServingsDeliveredTF.getText().trim() : "";

        if (w.isEmpty() || qty.isEmpty()) {
            if (DistributionStatusLabel != null) {
                DistributionStatusLabel.setText("Error: Ward and servings fields cannot be empty.");
            }
            return;
        }

        try {
            int delivered = Integer.parseInt(qty);
            int allocated = 100;

            String stat = "COMPLETED";
            if (delivered < allocated) {
                stat = "SHORTAGE";
            }

            // save record to the list
            distributionList.add(new DistributionRecord(w, allocated, delivered, stat));

            if (DistributionStatusLabel != null) {
                DistributionStatusLabel.setText("Record saved for " + w);
            }

            // clear text fields
            if (WardTF != null) WardTF.clear();
            if (ServingsDeliveredTF != null) ServingsDeliveredTF.clear();
            if (TimeTF != null) TimeTF.clear();

        } catch (NumberFormatException e) {
            if (DistributionStatusLabel != null) {
                DistributionStatusLabel.setText("Error: Enter a valid number for servings.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }

    // helper class for inner data rows
    public static class DistributionRecord {
        private String wardName;
        private int allocatedCount;
        private int distributedCount;
        private String deliveryStatus;

        public DistributionRecord(String wardName, int allocatedCount, int distributedCount, String deliveryStatus) {
            this.wardName = wardName;
            this.allocatedCount = allocatedCount;
            this.distributedCount = distributedCount;
            this.deliveryStatus = deliveryStatus;
        }

        public String getWardName() { return wardName; }
        public int getAllocatedCount() { return allocatedCount; }
        public int getDistributedCount() { return distributedCount; }
        public String getDeliveryStatus() { return deliveryStatus; }
    }
}