package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.CapacityMonitoring;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class CapacityMonitoringController {

    @javafx.fxml.FXML
    private TableColumn<CapacityMonitoring, String> statusTableColumn;

    @javafx.fxml.FXML
    private TextField capacityTextField;

    @javafx.fxml.FXML
    private TextField statustextField;

    @javafx.fxml.FXML
    private TableColumn<CapacityMonitoring, Integer> availableTableColumn;

    @javafx.fxml.FXML
    private TableColumn<CapacityMonitoring, String> cellBlockTableColumn;

    @javafx.fxml.FXML
    private TextField occupiedTextField;

    @javafx.fxml.FXML
    private TableColumn<CapacityMonitoring, Integer> capacityTableColumn;

    @javafx.fxml.FXML
    private TextField availableTextField;

    @javafx.fxml.FXML
    private TableView<CapacityMonitoring> capacityMonitoringTableView;

    @javafx.fxml.FXML
    private TableColumn<CapacityMonitoring, Integer> occupiedTableColumn;

    @javafx.fxml.FXML
    private TextField cellBlockTextField;


    @javafx.fxml.FXML
    public void initialize() {

        cellBlockTableColumn.setCellValueFactory(new PropertyValueFactory<>("cellBlock"));
        capacityTableColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        occupiedTableColumn.setCellValueFactory(new PropertyValueFactory<>("occupied"));
        availableTableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        capacityMonitoringTableView.getItems().clear();

        File file = new File("CapacityMonitoring.bin");

        if (!file.exists()) {
            Helper.showAlert("Information", "No capacity information found.");
            return;
        }

        try {

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    CapacityMonitoring c =
                            (CapacityMonitoring) ois.readObject();

                    capacityMonitoringTableView.getItems().add(c);

                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not load capacity information."
            );
        }
    }


    @javafx.fxml.FXML
    public void generateWarningButtonOA(ActionEvent actionEvent) {

        String cellBlock = cellBlockTextField.getText().trim();
        String capacityText = capacityTextField.getText().trim();
        String occupiedText = occupiedTextField.getText().trim();
        String availableText = availableTextField.getText().trim();
        String status = statustextField.getText().trim();


        if (cellBlock.isEmpty() ||
                capacityText.isEmpty() ||
                occupiedText.isEmpty() ||
                availableText.isEmpty() ||
                status.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        int capacity;
        int occupied;
        int available;


        try {

            capacity = Integer.parseInt(capacityText);
            occupied = Integer.parseInt(occupiedText);
            available = Integer.parseInt(availableText);

        } catch (NumberFormatException e) {

            Helper.showAlert(
                    "Error",
                    "Capacity, Occupied and Available must be numbers."
            );

            return;
        }


        CapacityMonitoring c = new CapacityMonitoring(
                cellBlock,
                capacity,
                occupied,
                available,
                status
        );


        File file = new File("CapacityMonitoring.bin");

        try {

            FileOutputStream fos;
            ObjectOutputStream oos;


            if (file.exists() && file.length() > 0) {

                fos = new FileOutputStream(file, true);

                oos = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() throws IOException {
                    }
                };

            } else {

                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(c);
            oos.close();


            Helper.showAlert(
                    "Success",
                    "Capacity information saved successfully."
            );


            clearFields();

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save capacity information."
            );
        }
    }


    private void clearFields() {

        cellBlockTextField.clear();
        capacityTextField.clear();
        occupiedTextField.clear();
        availableTextField.clear();
        statustextField.clear();
    }
}