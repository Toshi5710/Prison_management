package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.StaffRecruitment;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class StaffRecruitmentController {

    @javafx.fxml.FXML
    private ComboBox<String> securityClearanceComboBox;

    @javafx.fxml.FXML
    private TextField candidateIdTextField;


    @javafx.fxml.FXML
    public void initialize() {

        securityClearanceComboBox.getItems().addAll("Low", "Medium", "High");
    }


    @javafx.fxml.FXML
    public void approveButtonOA(ActionEvent actionEvent) {

        if (candidateIdTextField.getText().trim().isEmpty() || securityClearanceComboBox.getValue() == null) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        StaffRecruitment s = new StaffRecruitment(
                candidateIdTextField.getText().trim(),
                securityClearanceComboBox.getValue(),
                "Approved"
        );


        File f = new File("StaffRecruitment.bin");

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


            oos.writeObject(s);

            oos.close();


            Helper.showAlert(
                    "Success",
                    "Candidate approved successfully."
            );


            candidateIdTextField.clear();
            securityClearanceComboBox.getSelectionModel().clearSelection();


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save recruitment information."
            );
        }
    }


    @javafx.fxml.FXML
    public void rejectButtonOA(ActionEvent actionEvent) {

        if (candidateIdTextField.getText().trim().isEmpty() ||
                securityClearanceComboBox.getValue() == null) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        StaffRecruitment s = new StaffRecruitment(
                candidateIdTextField.getText().trim(),
                securityClearanceComboBox.getValue(),
                "Rejected"
        );


        File f = new File("StaffRecruitment.bin");

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


            oos.writeObject(s);

            oos.close();


            Helper.showAlert(
                    "Success",
                    "Candidate rejected successfully."
            );


            candidateIdTextField.clear();
            securityClearanceComboBox.getSelectionModel().clearSelection();


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save recruitment information."
            );
        }
    }
}