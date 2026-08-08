package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.IncidentReport;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class IncidentReportController {

    @javafx.fxml.FXML
    private TextField incidentIdTextField;

    @javafx.fxml.FXML
    private DatePicker datePicker;

    @javafx.fxml.FXML
    private TextArea descriptionTextArea;

    @javafx.fxml.FXML
    private ComboBox<String> incidentTypeComboBox;


    @javafx.fxml.FXML
    public void initialize() {

        incidentTypeComboBox.getItems().addAll("Security Incident", "Prisoner Incident", "Staff Incident", "Medical Incident", "Visitor Incident");
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        if (incidentIdTextField.getText().trim().isEmpty() ||
                datePicker.getValue() == null ||
                incidentTypeComboBox.getValue() == null ||
                descriptionTextArea.getText().trim().isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        IncidentReport i = new IncidentReport(
                incidentIdTextField.getText().trim(),
                datePicker.getValue(),
                incidentTypeComboBox.getValue(),
                descriptionTextArea.getText().trim()
        );


        File f = new File("IncidentReport.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists() && f.length() > 0) {

                fos = new FileOutputStream(f, true);

                oos = new ObjectOutputStream(fos) {
                    @Override
                    protected void writeStreamHeader() {
                        // Do not write another header
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
                    "Incident report saved successfully."
            );


            incidentIdTextField.clear();
            datePicker.setValue(null);
            incidentTypeComboBox.getSelectionModel().clearSelection();
            descriptionTextArea.clear();


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save incident report."
            );
        }
    }


    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {

        Helper.showAlert(
                "Incident Report",
                "Please save the incident report first."
        );
    }
}