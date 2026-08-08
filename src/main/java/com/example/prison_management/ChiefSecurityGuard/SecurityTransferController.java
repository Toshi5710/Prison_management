package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.TransferRequest;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SecurityTransferController
{
    @javafx.fxml.FXML
    private Label showInfoLabel;
    @javafx.fxml.FXML
    private TextField prisonerIDTextField;
    @javafx.fxml.FXML
    private ComboBox<String> destinationBlockComboBox;
    @javafx.fxml.FXML
    private AnchorPane statusTextField;
    @javafx.fxml.FXML
    private ComboBox<String> reasonComboBox;

    @javafx.fxml.FXML
    public void initialize() {

        destinationBlockComboBox.getItems().addAll(
                "Block 1",
                "Block 2",
                "Block 3",
                "Block 4",
                "Block 5"
        );

        reasonComboBox.getItems().addAll(
                "High Security Threat Isolation",
                "Medical Reason",
                "Administrative Transfer",
                "Security Reassignment"
        );
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Dashboard");
    }

    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {
        showInfoLabel.setText("");

        String prisonerID = prisonerIDTextField.getText().trim();
        String destination = destinationBlockComboBox.getValue();
        String reason = reasonComboBox.getValue();


        // Validate prisoner ID
        if (!prisonerID.matches("\\d{5}")) {
            showInfoLabel.setText(
                    "Prisoner ID must be exactly 5 digits."
            );
            return;
        }


        // Validate destination
        if (destination == null) {
            showInfoLabel.setText(
                    "Please select a destination block."
            );
            return;
        }


        // Validate reason
        if (reason == null) {
            showInfoLabel.setText(
                    "Please select a transfer reason."
            );
            return;
        }


        // Create transfer request
        TransferRequest request = new TransferRequest(
                prisonerID,
                destination,
                reason

        );


        File f = new File("TransferRequest.bin");

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {

            if (f.exists()) {

                fos = new FileOutputStream(f, true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);

            } else {

                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(request);
            oos.close();


            showInfoLabel.setText(
                    "Security transfer request submitted!"
            );


            // Clear fields
            prisonerIDTextField.clear();
            destinationBlockComboBox.getSelectionModel().clearSelection();
            reasonComboBox.getSelectionModel().clearSelection();


        } catch (Exception e) {

            e.printStackTrace();



        }





    }
    }
