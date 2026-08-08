package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class PrisonAccountantDashboardViewController {

    @FXML private AnchorPane mainContentPane;

    @FXML
    public void initialize() {
    }

    @FXML
    public void SalaryManagementButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/SalaryManagement.fxml");
    }

    @FXML
    public void FinancialReportsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/FinancialReports.fxml");
    }

    @FXML
    public void LogoutButtonOA(ActionEvent actionEvent) {
        try {
            java.net.URL fxmlUrl = getClass().getResource("/com/example/prison_management/Login.fxml");

            if (fxmlUrl == null) {
                System.err.println("Fatal: Login.fxml could not be found!");
                return;
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            System.err.println("Could not load Login window: " + e.getMessage());
        }
    }

    @FXML
    public void SupplierPaymentsButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/SupplierPaymentManagement.fxml");
    }

    @FXML
    public void InfrastructureExpansionButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/InfrastructureBudgetPlanning.fxml");
    }

    @FXML
    public void ExpenseManagementButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/ExpenseManagement.fxml");
    }

    @FXML
    public void PrisonerFinesButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/PrisonerFineManagement.fxml");
    }

    @FXML
    public void TransactionMonitoringButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/TransactionMonitoring.fxml");
    }

    @FXML
    public void BudgetManagementButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchView(mainContentPane, "/PrisonAccountant/BudgetManagement.fxml");
    }
}