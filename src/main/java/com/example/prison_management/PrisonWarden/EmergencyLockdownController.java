package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

import java.io.*;

public class EmergencyLockdownController {

    @javafx.fxml.FXML
    private CheckBox ActivateLockdownCheckBox;

    @javafx.fxml.FXML
    public void initialize() {
        File f = new File("LockdownStatus.bin");

        if (!f.exists() || f.length() == 0) {
            if (ActivateLockdownCheckBox != null) {
                ActivateLockdownCheckBox.setSelected(false);
            }
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            LockdownStatus status = (LockdownStatus) ois.readObject();


            if (ActivateLockdownCheckBox != null) {
                ActivateLockdownCheckBox.setSelected(status.isLockdownActive());
            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void SaveEmergencyLockdownStatusAndApplyOA(ActionEvent actionEvent) {
        if (ActivateLockdownCheckBox == null) {
            Helper.showErrorAlert("Checkbox component not injected properly!");
            return;
        }

        boolean isActive = ActivateLockdownCheckBox.isSelected();
        LockdownStatus status = new LockdownStatus(isActive);

        File f = new File("LockdownStatus.bin");

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(status);
            oos.close();

            if (isActive) {
                Helper.showSuccessAlert("Emergency Lockdown", "Emergency Lockdown is now ACTIVE!");
            } else {
                Helper.showSuccessAlert("Emergency Lockdown", "Emergency Lockdown has been deactivated!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Helper.showErrorAlert("Failed to save lockdown status.");
        }
    }

    @javafx.fxml.FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "PrisonWardenDashBoard");
    }
}