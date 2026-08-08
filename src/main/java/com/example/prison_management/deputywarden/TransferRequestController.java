package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.TransferRequest;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class TransferRequestController {

    @javafx.fxml.FXML
    private TableColumn<TransferRequest, String> statusTableColumn;

    @javafx.fxml.FXML
    private TableColumn<TransferRequest, String> transferTypeTableColumn;

    @javafx.fxml.FXML
    private TableColumn<TransferRequest, String> requestIdTableColumn;

    @javafx.fxml.FXML
    private CheckBox approveCheckBox;

    @javafx.fxml.FXML
    private TableColumn<TransferRequest, String> reasonTableColumn;

    @javafx.fxml.FXML
    private TextField prisonerIdTextField;

    @javafx.fxml.FXML
    private TextField statusTextField;

    @javafx.fxml.FXML
    private TableColumn<TransferRequest, String> prisonerIdTableColumn;

    @javafx.fxml.FXML
    private TextField transferTypeTextField;

    @javafx.fxml.FXML
    private TextField reasonTextField;

    @javafx.fxml.FXML
    private TableView<TransferRequest> transferRequestTableView;

    @javafx.fxml.FXML
    private TextField requestIdTextField;


    @javafx.fxml.FXML
    public void initialize() {

        requestIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        prisonerIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
        transferTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("transferType"));
        reasonTableColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadTransferRequests();
    }


    private void loadTransferRequests() {

        transferRequestTableView.getItems().clear();

        File file = new File("TransferRequest.bin");

        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    TransferRequest request =
                            (TransferRequest) ois.readObject();

                    transferRequestTableView.getItems().add(request);

                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not load transfer requests."
            );
        }
    }


    @javafx.fxml.FXML
    public void submitDecisionButtonOA(ActionEvent actionEvent) {

        String requestId = requestIdTextField.getText().trim();
        String prisonerId = prisonerIdTextField.getText().trim();
        String transferType = transferTypeTextField.getText().trim();
        String reason = reasonTextField.getText().trim();

        if (requestId.isEmpty() ||
                prisonerId.isEmpty() ||
                transferType.isEmpty() ||
                reason.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }

        String status;

        if (approveCheckBox.isSelected()) {
            status = "Approved";
        } else {
            status = "Rejected";
        }

        statusTextField.setText(status);

        TransferRequest transferRequest =
                new TransferRequest(
                        requestId,
                        prisonerId,
                        transferType,
                        reason,
                        status
                );

        File file = new File("TransferRequest.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists() && file.length() > 0) {

                fos = new FileOutputStream(file, true);

                oos = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() {

                    }
                };

            } else {

                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(transferRequest);
            oos.close();

            transferRequestTableView.getItems().add(transferRequest);

            Helper.showAlert(
                    "Success",
                    "Transfer decision submitted successfully."
            );

            clearFields();

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save transfer request."
            );
        }
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        clearFields();
    }


    private void clearFields() {

        requestIdTextField.clear();
        prisonerIdTextField.clear();
        transferTypeTextField.clear();
        reasonTextField.clear();
        statusTextField.clear();
        approveCheckBox.setSelected(false);
    }
}