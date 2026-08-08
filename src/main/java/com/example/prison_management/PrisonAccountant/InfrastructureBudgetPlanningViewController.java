package com.example.prison_management.PrisonAccountant;

import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InfrastructureBudgetPlanningViewController {

    @FXML private TextField FacilityExpansionProjectNameTF;
    @FXML private TextField ProjectedConstructionAndOperationalCostsTF;
    @FXML private TextArea InfrastructureBudgetPlanningTA;
    @FXML private Label ConfirmationLabel;

    @FXML
    public void initialize() {
        if (ConfirmationLabel != null) {
            ConfirmationLabel.setText("");
        }
        if (FacilityExpansionProjectNameTF != null) {
            FacilityExpansionProjectNameTF.setPromptText("e.g. Cell Block D Expansion");
        }
        if (ProjectedConstructionAndOperationalCostsTF != null) {
            ProjectedConstructionAndOperationalCostsTF.setPromptText("e.g. 750000.00");
        }
        if (InfrastructureBudgetPlanningTA != null) {
            InfrastructureBudgetPlanningTA.setEditable(false);
            InfrastructureBudgetPlanningTA.setText("Enter project specifications to calculate financial summaries.");
        }
    }

    @FXML
    public void CalculateCostsButtonOA(ActionEvent actionEvent) {
        String projectName = (FacilityExpansionProjectNameTF != null) ? FacilityExpansionProjectNameTF.getText().trim() : "";
        String costText = (ProjectedConstructionAndOperationalCostsTF != null) ? ProjectedConstructionAndOperationalCostsTF.getText().trim() : "";

        if (projectName.isEmpty() || costText.isEmpty()) {
            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: red;");
                ConfirmationLabel.setText("Error: Both fields must be filled.");
            }
            return;
        }

        try {
            double baseCost = Double.parseDouble(costText);
            if (baseCost <= 0) {
                if (ConfirmationLabel != null) {
                    ConfirmationLabel.setStyle("-fx-text-fill: red;");
                    ConfirmationLabel.setText("Error: Cost must be positive.");
                }
                return;
            }

            // basic margin math
            double contingency = baseCost * 0.12;
            double tax = baseCost * 0.05;
            double totalCost = baseCost + contingency + tax;

            StringBuilder costSummary = new StringBuilder();
            costSummary.append("Infrastructure Proposal Summary\n");
            costSummary.append("---------------------------------\n");
            costSummary.append("Project: ").append(projectName).append("\n");
            costSummary.append("Base Cost: $").append(String.format("%.2f", baseCost)).append("\n");
            costSummary.append("Contingency Buffer (12%): $").append(String.format("%.2f", contingency)).append("\n");
            costSummary.append("Regulatory Compliance (5%): $").append(String.format("%.2f", tax)).append("\n");
            costSummary.append("---------------------------------\n");
            costSummary.append("TOTAL REQUESTED BUDGET: $").append(String.format("%.2f", totalCost));

            if (InfrastructureBudgetPlanningTA != null) {
                InfrastructureBudgetPlanningTA.setText(costSummary.toString());
            }

            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: green;");
                ConfirmationLabel.setText("Calculations updated.");
            }

        } catch (NumberFormatException e) {
            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: red;");
                ConfirmationLabel.setText("Error: Enter a valid number for cost.");
            }
        }
    }

    @FXML
    public void SubmitProposaltoWardenButtonOA(ActionEvent actionEvent) {
        String generatedContent = (InfrastructureBudgetPlanningTA != null) ? InfrastructureBudgetPlanningTA.getText() : "";

        if (generatedContent.isEmpty() || generatedContent.contains("Enter project specifications")) {
            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: red;");
                ConfirmationLabel.setText("Error: Calculate the budget before submitting.");
            }
            return;
        }

        // write summary out to text file
        try (FileWriter fw = new FileWriter("budget_proposals.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(generatedContent);
            pw.println("\n--- END OF SUBMISSION ---\n");

            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: green;");
                ConfirmationLabel.setText("Proposal saved to budget_proposals.txt");
            }

            if (FacilityExpansionProjectNameTF != null) FacilityExpansionProjectNameTF.clear();
            if (ProjectedConstructionAndOperationalCostsTF != null) ProjectedConstructionAndOperationalCostsTF.clear();

        } catch (IOException e) {
            if (ConfirmationLabel != null) {
                ConfirmationLabel.setStyle("-fx-text-fill: red;");
                ConfirmationLabel.setText("Error: Could not save proposal to file.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonAccountant/PrisonAccountantDashboard.fxml", "Prison Accountant Dashboard");
    }
}