package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.Notification;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class NotificationController {

    @javafx.fxml.FXML
    private CheckBox emailNotificationCheckBox;

    @javafx.fxml.FXML
    private CheckBox smsNotificationCheckBox;


    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        boolean emailNotification =
                emailNotificationCheckBox.isSelected();

        boolean smsNotification =
                smsNotificationCheckBox.isSelected();


        if (!emailNotification && !smsNotification) {

            Helper.showAlert(
                    "Error",
                    "Please select at least one notification option."
            );

            return;
        }


        Notification n = new Notification(
                emailNotification,
                smsNotification
        );


        File f = new File("Notification.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists() && f.length() > 0) {

                fos = new FileOutputStream(f, true);

                oos = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() {
                    }
                };

            } else {

                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(n);
            oos.close();


            Helper.showAlert(
                    "Success",
                    "Notification settings saved successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save notification settings."
            );
        }
    }


    @javafx.fxml.FXML
    public void sendTextNotificationButtonOA(ActionEvent actionEvent) {

        if (!smsNotificationCheckBox.isSelected()) {

            Helper.showAlert(
                    "Warning",
                    "Please select SMS Notification first."
            );

            return;
        }

        Helper.showAlert(
                "Success",
                "Text notification sent successfully."
        );
    }
}