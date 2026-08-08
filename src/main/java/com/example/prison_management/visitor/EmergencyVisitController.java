package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.EmergencyVisit;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class EmergencyVisitController {

    @javafx.fxml.FXML
    private TextArea emergencyReasonTextArea;

    @javafx.fxml.FXML
    private TextField documentPathTextField;


    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void uploadButtonOA(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select Supporting Document");

        File file = fileChooser.showOpenDialog(
                documentPathTextField.getScene().getWindow()
        );

        if (file != null) {

            documentPathTextField.setText(file.getAbsolutePath());

        }
    }


    @javafx.fxml.FXML
    public void submitRequestButtonOA(ActionEvent actionEvent) {

        String emergencyReason =
                emergencyReasonTextArea.getText().trim();

        String documentPath =
                documentPathTextField.getText().trim();


        if (emergencyReason.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter the emergency reason."
            );

            return;
        }


        if (documentPath.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please upload a supporting document."
            );

            return;
        }


        EmergencyVisit e = new EmergencyVisit(
                emergencyReason,
                documentPath
        );


        File f = new File("EmergencyVisit.bin");

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


            oos.writeObject(e);

            oos.close();


            Helper.showAlert(
                    "Success",
                    "Emergency visit request submitted successfully."
            );


            emergencyReasonTextArea.clear();
            documentPathTextField.clear();


        } catch (Exception ex) {

            ex.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save emergency visit request."
            );
        }
    }
}