package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.ShiftLeaveRequest;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class ShiftLeaveRequestsController
{
    @javafx.fxml.FXML
    private TableColumn<ShiftLeaveRequest, String> statusTableColumn;
    @javafx.fxml.FXML
    private TableColumn<ShiftLeaveRequest, String> reasonTableColumn;
    @javafx.fxml.FXML
    private TableColumn <ShiftLeaveRequest, String>officerIDTableColumn;
    @javafx.fxml.FXML
    private Label showMessageLabel;
    @javafx.fxml.FXML
    private AnchorPane me;
    @javafx.fxml.FXML
    private TableColumn<ShiftLeaveRequest, String> requestIDTableColumn;
    @javafx.fxml.FXML
    private TableView<ShiftLeaveRequest> showAllRequestsTableView;

    @javafx.fxml.FXML
    public void initialize() {
        requestIDTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("requestID")
        );

        officerIDTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("officerID")
        );

        reasonTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("reason")
        );

        statusTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

    }

    @javafx.fxml.FXML
    public void showAllRequestResultsButtonOA(ActionEvent actionEvent) {

        showMessageLabel.setText("");

        showAllRequestsTableView.getItems().clear();

        File f = new File("LeaveRequest.bin");

        if (!f.exists()) {
            showMessageLabel.setText("No leave requests found.");
            return;
        }

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    ShiftLeaveRequest request =
                            (ShiftLeaveRequest) ois.readObject();

                    showAllRequestsTableView.getItems().add(request);

                } catch (EOFException e) {

                    break;
                }
            }

            ois.close();

            showMessageLabel.setText(
                    "Leave requests loaded successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();


        }




    }

    @javafx.fxml.FXML
    public void approveLeaveButtonOA(ActionEvent actionEvent) {
        ShiftLeaveRequest selectedRequest =
                showAllRequestsTableView
                        .getSelectionModel()
                        .getSelectedItem();

        if (selectedRequest == null) {

            showMessageLabel.setText(
                    "Please select a leave request."
            );

            return;
        }

        selectedRequest.setStatus("Approved");

        saveUpdatedRequest(selectedRequest);

        showAllRequestsTableView.refresh();

        showMessageLabel.setText(
                "Leave request approved successfully!"
        );
    }

    @javafx.fxml.FXML
    public void denyLeaveButtonOA(ActionEvent actionEvent) {
        ShiftLeaveRequest selectedRequest =
                showAllRequestsTableView
                        .getSelectionModel()
                        .getSelectedItem();

        if (selectedRequest == null) {

            showMessageLabel.setText(
                    "Please select a leave request."
            );

            return;
        }

        selectedRequest.setStatus("Denied");

        saveUpdatedRequest(selectedRequest);

        showAllRequestsTableView.refresh();

        showMessageLabel.setText(
                "Leave request denied!"
        );
    }
    private void saveUpdatedRequest(ShiftLeaveRequest selectedRequest) {

        File f = new File("LeaveRequest.bin");

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            java.util.ArrayList<ShiftLeaveRequest> requests =
                    new java.util.ArrayList<>();

            while (true) {

                try {

                    ShiftLeaveRequest request =
                            (ShiftLeaveRequest) ois.readObject();

                    if (request.getRequestID().equals(
                            selectedRequest.getRequestID())) {

                        request.setStatus(
                                selectedRequest.getStatus()
                        );
                    }

                    requests.add(request);

                } catch (EOFException e) {

                    break;
                }
            }

            ois.close();


            FileOutputStream fos =
                    new FileOutputStream(f);

            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);


            for (ShiftLeaveRequest request : requests) {

                oos.writeObject(request);
            }

            oos.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }





    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Shift Leave");


    }
}