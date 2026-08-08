package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.CancelAppointment;
import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CancelAppointmentController {

    @javafx.fxml.FXML
    private TextField tokenTextField;


    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void cancelAppointmentButtonOA(ActionEvent actionEvent) {

        String token = tokenTextField.getText().trim();

        if (token.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter Appointment Token."
            );

            return;
        }

        CancelAppointment c = new CancelAppointment(
                token
        );

        File f = new File("CancelAppointment.bin");

        try {

            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(c);

            oos.close();

            Helper.showAlert(
                    "Success",
                    "Appointment cancellation request saved successfully."
            );

            tokenTextField.clear();

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save cancellation information."
            );
        }
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/VisitorDashboard.fxml",
                "Visitor Dashboard"
        );
    }
}