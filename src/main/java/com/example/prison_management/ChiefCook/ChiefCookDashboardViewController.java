package com.example.prison_management.ChiefCook;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ChiefCookDashboardViewController {

    @FXML private AnchorPane mainContentPane;

    @FXML
    public void MealScheduleButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/MealScheduleManagement.fxml");
    }

    @FXML
    public void FoodInventoryButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/FoodInventory.fxml");
    }

    @FXML
    public void MealDistributionButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/MealDistribution.fxml");
    }

    @FXML
    public void FoodConsumptionButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/FoodConsumptionReports.fxml");
    }

    @FXML
    public void KitchenReportsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/KitchenReports.fxml");
    }

    @FXML
    public void SupplyRequestsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/SupplyRequestManagement.fxml");
    }

    @FXML
    public void SpecialDietsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/SpecialDietManagement.fxml");
    }

    @FXML
    public void StaffAssignmentsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/ChiefCook/KitchenStaffAssignment.fxml");
    }

    @FXML
    public void LogoutButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/com/example/prison_management/Login.fxml", "Login");
    }
}