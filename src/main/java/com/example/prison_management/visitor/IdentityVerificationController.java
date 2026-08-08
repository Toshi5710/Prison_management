package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.IdentityVerification;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class IdentityVerificationController {

    @javafx.fxml.FXML
    private TextField appointmentTokenTextField;


    private boolean verified = false;


    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void verifyButtonOA(ActionEvent actionEvent) {

        String appointmentToken =
                appointmentTokenTextField.getText().trim();

        if (appointmentToken.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter appointment token."
            );

            return;
        }

        verified = true;

        Helper.showAlert(
                "Verification",
                "Appointment token verified successfully."
        );
    }


    @javafx.fxml.FXML
    public void grantEntryButtonOA(ActionEvent actionEvent) {

        String appointmentToken =
                appointmentTokenTextField.getText().trim();

        if (appointmentToken.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter appointment token."
            );

            return;
        }

        if (!verified) {

            Helper.showAlert(
                    "Warning",
                    "Please verify the appointment token first."
            );

            return;
        }


        IdentityVerification i = new IdentityVerification(
                appointmentToken,
                "Verified",
                "Entry Granted"
        );


        File f = new File("IdentityVerification.bin");

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

            oos.writeObject(i);

            oos.close();


            Helper.showAlert(
                    "Success",
                    "Entry granted successfully."
            );

            appointmentTokenTextField.clear();
            verified = false;

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save entry information."
            );
        }
    }
}