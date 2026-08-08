package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;

public class PolicyManagementController {

    @javafx.fxml.FXML
    private TextField EditThePolicyDescriptionTF;
    @javafx.fxml.FXML
    private ComboBox<String> PolicyIDComboBox;
    @javafx.fxml.FXML
    private Label CurrentpolicyLabel;

    @javafx.fxml.FXML
    public void initialize() {
        PolicyIDComboBox.getItems().addAll("Policy 1", "Policy 2", "Policy 3");
    }

    @javafx.fxml.FXML
    public void UpdatePolicyOA(ActionEvent actionEvent) {
        String selectedPolicy = PolicyIDComboBox.getValue();
        String newDescription = EditThePolicyDescriptionTF.getText();

        // Validations
        if (selectedPolicy == null) {
            Helper.showErrorAlert("Please select a policy to update.");
            return;
        }

        if (newDescription.isEmpty()) {
            Helper.showErrorAlert("Policy description cannot be empty!");
            return;
        }

        Policy policyToUpdate = new Policy(selectedPolicy, "");
        policyToUpdate.updateContent(newDescription);

        File f = new File("policy.bin");
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            if (f.exists() && f.length() > 0) {
                fos = new FileOutputStream(f, true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(policyToUpdate);
            oos.close();

            CurrentpolicyLabel.setText(newDescription);
            EditThePolicyDescriptionTF.clear();
            Helper.showSuccessAlert("Success", "Policy updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Failed to save policy to file.");
        }
    }

    @javafx.fxml.FXML
    public void ShowcurrentPolicyOA(ActionEvent actionEvent) {
        String selectedPolicy = PolicyIDComboBox.getValue();

        if (selectedPolicy == null) {
            Helper.showErrorAlert("Please select a policy from the dropdown first.");
            return;
        }

        File f = new File("policy.bin");

        if (!f.exists() || f.length() == 0) {
            CurrentpolicyLabel.setText("No policy description set yet (Empty).");
            return;
        }

        String latestDescription = null;

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Policy p = (Policy) ois.readObject();
                    if (p.getPolicyId().equals(selectedPolicy)) {
                        latestDescription = p.getDescription();
                    }
                } catch (EOFException e) {
                    break; // Reached end of binary file
                }
            }

            ois.close();

            if (latestDescription != null) {
                CurrentpolicyLabel.setText(latestDescription);
            } else {
                CurrentpolicyLabel.setText("No policy description set yet (Empty).");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Error reading policy binary file.");
        }
    }

    @javafx.fxml.FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "PrisonWardenDashBoard");
    }
}