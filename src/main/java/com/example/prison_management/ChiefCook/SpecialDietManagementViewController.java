package com.example.prison_management.ChiefCook;

import com.example.prison_management.mainuser.DietPlan;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SpecialDietManagementViewController {

    @FXML private TextField PrisonerIDTF;
    @FXML private TextArea SpeacialDietManagementTA;
    @FXML private Label ApprovedDietPlanstatusLabel;

    @FXML private TableView<DietPlan> SpeacialDietManagementTV;
    @FXML private TableColumn<DietPlan, String> PrisonerIDTC;
    @FXML private TableColumn<DietPlan, String> MedicalRecommendationTC;
    @FXML private TableColumn<DietPlan, String> DietPlanTC;

    private final ObservableList<DietPlan> dietPlanDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (PrisonerIDTF != null) {
            PrisonerIDTF.setPromptText("e.g. PRIS-901");
        }
        if (SpeacialDietManagementTA != null) {
            SpeacialDietManagementTA.setPromptText("Enter specific dietary limitations, allergies, or physician notes here...");
        }
        if (ApprovedDietPlanstatusLabel != null) {
            ApprovedDietPlanstatusLabel.setText("");
        }

        // set up table columns
        if (SpeacialDietManagementTV != null) {
            PrisonerIDTC.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
            MedicalRecommendationTC.setCellValueFactory(new PropertyValueFactory<>("medicalRestrictions"));
            DietPlanTC.setCellValueFactory(new PropertyValueFactory<>("planId"));

            SpeacialDietManagementTV.setItems(dietPlanDataList);
        }

        // sample rows
        dietPlanDataList.add(new DietPlan("PLAN-501", "PRIS-104", "Low Sodium - Hypertension protocol"));
        dietPlanDataList.add(new DietPlan("PLAN-502", "PRIS-308", "No Dairy / Severe Lactose Intolerance"));
    }

    @FXML
    public void FetchMedicalRecommendationsButtonOA(ActionEvent actionEvent) {
        String id = (PrisonerIDTF != null) ? PrisonerIDTF.getText().trim() : "";

        if (id.isEmpty()) {
            if (ApprovedDietPlanstatusLabel != null) {
                ApprovedDietPlanstatusLabel.setText("Please enter a valid Prisoner ID first!");
            }
            return;
        }

        if (ApprovedDietPlanstatusLabel != null) {
            ApprovedDietPlanstatusLabel.setText("Medical data loaded for " + id);
        }
        if (SpeacialDietManagementTA != null) {
            SpeacialDietManagementTA.setText("Diabetic Meal Plan: Avoid refined sugars. Target 1800 kcal limit.");
        }
    }

    @FXML
    public void CreateAndSaveSpecialDietButtonOA(ActionEvent actionEvent) {
        String prisonerId = (PrisonerIDTF != null) ? PrisonerIDTF.getText().trim() : "";

        String notes = "";
        if (SpeacialDietManagementTA != null && SpeacialDietManagementTA.getText() != null) {
            notes = SpeacialDietManagementTA.getText().trim();
        }

        if (prisonerId.isEmpty() || notes.isEmpty()) {
            if (ApprovedDietPlanstatusLabel != null) {
                ApprovedDietPlanstatusLabel.setText("Error: Fields cannot be blank.");
            }
            return;
        }

        // basic random id from system time
        String timeStr = String.valueOf(System.currentTimeMillis());
        String planId = "PLAN-" + timeStr.substring(timeStr.length() - 3);

        // create object using your model constructor
        DietPlan newDietPlan = new DietPlan(planId, prisonerId, notes);

        // check model verification method
        if (!newDietPlan.verifyDietRequirements()) {
            if (ApprovedDietPlanstatusLabel != null) {
                ApprovedDietPlanstatusLabel.setText("Error: Model validation failed.");
            }
            return;
        }

        // add to display list
        dietPlanDataList.add(newDietPlan);

        if (ApprovedDietPlanstatusLabel != null) {
            ApprovedDietPlanstatusLabel.setText("Diet plan registered successfully.");
        }

        // clear input items
        if (PrisonerIDTF != null) PrisonerIDTF.clear();
        if (SpeacialDietManagementTA != null) SpeacialDietManagementTA.clear();
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}