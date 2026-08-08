package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.VisitorAppointment;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class VisitorAppointmentController {

    @javafx.fxml.FXML
    private TextField phoneTextField;

    @javafx.fxml.FXML
    private ComboBox<String> prisonerComboBox;

    @javafx.fxml.FXML
    private TextField nameTextField;

    @javafx.fxml.FXML
    private DatePicker datePicker;

    @javafx.fxml.FXML
    private TextField nationalIdTextField;


    @javafx.fxml.FXML
    public void initialize() {

        prisonerComboBox.getItems().addAll("Prisoner 1", "Prisoner 2", "Prisoner 3");
    }


    @javafx.fxml.FXML
    public void requestAppointmentButtonOA(ActionEvent actionEvent) {

        String name = nameTextField.getText().trim();
        String nationalId = nationalIdTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String prisoner = prisonerComboBox.getValue();

        if (name.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter your name."
            );

            return;
        }

        if (nationalId.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter your National ID."
            );

            return;
        }

        if (phone.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter your phone number."
            );

            return;
        }

        if (prisoner == null) {

            Helper.showAlert(
                    "Error",
                    "Please select a prisoner."
            );

            return;
        }

        if (datePicker.getValue() == null) {

            Helper.showAlert(
                    "Error",
                    "Please select an appointment date."
            );

            return;
        }


        VisitorAppointment v = new VisitorAppointment(
                name,
                nationalId,
                phone,
                prisoner,
                datePicker.getValue()
        );


        File f = new File("VisitorAppointment.bin");

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

            oos.writeObject(v);
            oos.close();


            Helper.showAlert(
                    "Success",
                    "Appointment request submitted successfully."
            );


            nameTextField.clear();
            nationalIdTextField.clear();
            phoneTextField.clear();
            prisonerComboBox.getSelectionModel().clearSelection();
            datePicker.setValue(null);


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save appointment request."
            );
        }
    }
}