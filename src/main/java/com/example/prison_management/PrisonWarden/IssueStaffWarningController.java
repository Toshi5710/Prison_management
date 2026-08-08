package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;

public class IssueStaffWarningController {

    @FXML
    private TextField InputEmployeeIDTF;
    @FXML
    private TextField WarningtextTF;
    @FXML
    private Label LastWarningLabel;

    @FXML
    public void initialize() {

        File f = new File("StaffWarning.bin");
        if (!f.exists() || f.length() == 0) {
            try {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(new StaffWarning("Emp1", "No warnings issued"));
                oos.writeObject(new StaffWarning("Emp2", "No warnings issued"));
                oos.writeObject(new StaffWarning("Emp3", "No warnings issued"));
                oos.writeObject(new StaffWarning("Emp4", "No warnings issued"));

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void ShowlastWarningOA(ActionEvent actionEvent) {
        String empId = InputEmployeeIDTF.getText().trim();

        if (empId.isEmpty()) {
            Helper.showErrorAlert("Please enter an Employee ID!");
            return;
        }

        File f = new File("StaffWarning.bin");
        if (!f.exists() || f.length() == 0) {
            LastWarningLabel.setText("Last Warning ; No records found");
            return;
        }

        StaffWarning lastWarning = null;

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    StaffWarning w = (StaffWarning) ois.readObject();
                    if (w.getEmployeeId().equalsIgnoreCase(empId)) {
                        lastWarning = w;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();

            if (lastWarning != null) {
                LastWarningLabel.setText("Last Warning ; " + lastWarning.getWarningText());
            } else {
                LastWarningLabel.setText("Last Warning ; Employee ID not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Error reading warning binary file.");
        }
    }

    @FXML
    public void SendWarningOA(ActionEvent actionEvent) {
        String empId = InputEmployeeIDTF.getText().trim();
        String warningText = WarningtextTF.getText().trim();

        if (empId.isEmpty() || warningText.isEmpty()) {
            Helper.showErrorAlert("Please fill in all fields!");
            return;
        }

        StaffWarning newWarning = new StaffWarning(empId, warningText);

        File f = new File("StaffWarning.bin");
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

            oos.writeObject(newWarning);
            oos.close();

            Helper.showSuccessAlert("Success", "Warning sent to staff account");

            LastWarningLabel.setText("Last Warning ; " + warningText);
            WarningtextTF.clear();

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Failed to save warning to file!");
        }
    }

    @FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "PrisonWardenDashBoard");
    }
}