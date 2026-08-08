package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class AccountRevocationsController {

    @FXML
    private TextField InputTargetEmployeeIDTF;
    @FXML
    private CheckBox ActiveAccountCheckBox;

    @FXML
    public void initialize() {
        File f = new File("StaffAccount.bin");
        if (!f.exists() || f.length() == 0) {
            try {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(new StaffAccount("Emp1", true));
                oos.writeObject(new StaffAccount("Emp2", true));
                oos.writeObject(new StaffAccount("Emp3", true));
                oos.writeObject(new StaffAccount("Emp4", true));
                oos.writeObject(new StaffAccount("Emp5", true));

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void SaveAccessSettingsOA(ActionEvent actionEvent) {
        String empId = InputTargetEmployeeIDTF.getText().trim();

        if (empId.isEmpty()) {
            Helper.showErrorAlert("Please enter a target Employee ID!");
            return;
        }

        File f = new File("StaffAccount.bin");
        if (!f.exists() || f.length() == 0) {
            Helper.showErrorAlert("No staff accounts found!");
            return;
        }

        ArrayList<StaffAccount> list = new ArrayList<>();
        boolean found = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    StaffAccount acc = (StaffAccount) ois.readObject();
                    if (acc.getEmployeeId().equalsIgnoreCase(empId)) {
                        found = true;

                        // Check if trying to block an already blocked account
                        if (!acc.isActiveStatus() && !ActiveAccountCheckBox.isSelected()) {
                            Helper.showErrorAlert("Account is already blocked!");
                            return;
                        }

                        acc.setActiveStatus(ActiveAccountCheckBox.isSelected());
                    }
                    list.add(acc);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Error reading StaffAccount.bin");
            return;
        }

        if (!found) {
            Helper.showErrorAlert("Employee ID not found!");
            return;
        }


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            for (StaffAccount acc : list) {
                oos.writeObject(acc);
            }

            if (!ActiveAccountCheckBox.isSelected()) {
                Helper.showSuccessAlert("Success", "Staff member's Account has been blocked!");
            } else {
                Helper.showSuccessAlert("Success", "Staff member's Account access is active.");
            }

            InputTargetEmployeeIDTF.clear();
            ActiveAccountCheckBox.setSelected(false);

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Failed to save settings!");
        }
    }

    @FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "PrisonWardenDashBoard");
    }

    @FXML
    public void ShowStatusOA(ActionEvent actionEvent) {
        String empId = InputTargetEmployeeIDTF.getText().trim();

        if (empId.isEmpty()) {
            Helper.showErrorAlert("Please enter an Employee ID first!");
            return;
        }

        File f = new File("StaffAccount.bin");
        if (!f.exists() || f.length() == 0) {
            Helper.showErrorAlert("No staff account records found!");
            return;
        }

        boolean found = false;

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    StaffAccount acc = (StaffAccount) ois.readObject();
                    if (acc.getEmployeeId().equalsIgnoreCase(empId)) {
                        ActiveAccountCheckBox.setSelected(acc.isActiveStatus());
                        found = true;
                        break;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();

            if (!found) {
                Helper.showErrorAlert("Employee ID not found!");
                ActiveAccountCheckBox.setSelected(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Error reading StaffAccount.bin");
        }
    }
}