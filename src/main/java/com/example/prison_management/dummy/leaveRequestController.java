package com.example.prison_management.dummy;

import com.example.prison_management.mainuser.ShiftLeaveRequest;
import com.example.prison_management.utils.BinaryFileUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class leaveRequestController
{
    @javafx.fxml.FXML
    private TextField officerIDtextField;
    @javafx.fxml.FXML
    private ComboBox<String> leaveRequestComboBox;
    @javafx.fxml.FXML
    private TextField statusTextField;
    @javafx.fxml.FXML
    private TextField requestIDTextField;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
        leaveRequestComboBox.getItems().addAll("Family Reason,", "Personal Reason",
                "Official Reason", "Medical Reason");


        statusTextField.setText("Pending");
    }

    @javafx.fxml.FXML
    public void createLeaveRequestButtonOA(ActionEvent actionEvent) {

        messageLabel.setText("");

        String officerID = officerIDtextField.getText().trim();
        String requestID = requestIDTextField.getText().trim();
        String reason = leaveRequestComboBox.getValue();
        String status = statusTextField.getText().trim();


        // Validate Officer ID
        if (officerID.isEmpty()) {
            messageLabel.setText("Please enter Officer ID.");
            return;
        }


        // Validate Request ID
        if (requestID.isEmpty()) {
            messageLabel.setText("Please enter Request ID.");
            return;
        }


        // Validate leave reason
        if (reason == null) {
            messageLabel.setText("Please select a leave reason.");
            return;
        }


        // Create LeaveRequest object
        ShiftLeaveRequest request = new ShiftLeaveRequest(
                officerID,
                requestID,
                reason,
                status
        );


        File f = new File("LeaveRequest.bin");

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


            messageLabel.setText(
                    "Leave request created successfully!"
            );


            // Clear fields
            officerIDtextField.clear();
            requestIDTextField.clear();
            leaveRequestComboBox.getSelectionModel().clearSelection();

            // Keep status as Pending
            statusTextField.setText("Pending");


        } catch (Exception e) {

            e.printStackTrace();



        }
    }




}
