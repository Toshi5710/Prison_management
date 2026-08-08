package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.VisitorRecords;
import com.example.prison_management.utils.Helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class VisitorRecordsController {

    @javafx.fxml.FXML
    private TableColumn<VisitorRecords, String> statusTableColumn;

    @javafx.fxml.FXML
    private TextField prisonerNameTextField;

    @javafx.fxml.FXML
    private TableColumn<VisitorRecords, String> visitorIdTableColumn;

    @javafx.fxml.FXML
    private TableColumn<VisitorRecords, String> visitorNameTableColumn;

    @javafx.fxml.FXML
    private TextField visitorIdTextField;

    @javafx.fxml.FXML
    private TableColumn<VisitorRecords, String> prisonerNameTableColumn;

    @javafx.fxml.FXML
    private TextField statusTextField;

    @javafx.fxml.FXML
    private TextArea reviewCommentsTextArea;

    @javafx.fxml.FXML
    private TableColumn<VisitorRecords, String> visitDateTableColumn;

    @javafx.fxml.FXML
    private TableView<VisitorRecords> visitorRecordsTableView;

    @javafx.fxml.FXML
    private TextField visitorNameTextField;

    @javafx.fxml.FXML
    private TextField visitDateTextField;


    @javafx.fxml.FXML
    public void initialize() {

        visitorIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("visitorId"));
        visitorNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        prisonerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("prisonerName"));
        visitDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadVisitorRecords();
    }


    private void loadVisitorRecords() {

        File file = new File("VisitorRecords.bin");

        if (!file.exists() || file.length() == 0) {
            return;
        }

        ObservableList<VisitorRecords> records =
                FXCollections.observableArrayList();

        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            while (true) {

                try {

                    VisitorRecords record =
                            (VisitorRecords) ois.readObject();

                    records.add(record);

                } catch (EOFException e) {
                    break;
                }
            }

            visitorRecordsTableView.setItems(records);

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not load visitor records."
            );
        }
    }


    @javafx.fxml.FXML
    public void saveCommentButtonOA(ActionEvent actionEvent) {

        String visitorId = visitorIdTextField.getText().trim();
        String visitorName = visitorNameTextField.getText().trim();
        String prisonerName = prisonerNameTextField.getText().trim();
        String visitDate = visitDateTextField.getText().trim();
        String status = statusTextField.getText().trim();
        String reviewComments = reviewCommentsTextArea.getText().trim();


        if (visitorId.isEmpty()
                || visitorName.isEmpty()
                || prisonerName.isEmpty()
                || visitDate.isEmpty()
                || status.isEmpty()
                || reviewComments.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        VisitorRecords visitorRecords =
                new VisitorRecords(
                        visitorId,
                        visitorName,
                        prisonerName,
                        visitDate,
                        status,
                        reviewComments
                );


        File file = new File("VisitorRecords.bin");

        try {

            ObservableList<VisitorRecords> records =
                    FXCollections.observableArrayList();



            if (file.exists() && file.length() > 0) {

                try (
                        FileInputStream fis =
                                new FileInputStream(file);

                        ObjectInputStream ois =
                                new ObjectInputStream(fis)
                ) {

                    while (true) {

                        try {

                            VisitorRecords oldRecord =
                                    (VisitorRecords) ois.readObject();

                            records.add(oldRecord);

                        } catch (EOFException e) {
                            break;
                        }
                    }
                }
            }



            records.add(visitorRecords);

            try (
                    FileOutputStream fos =
                            new FileOutputStream(file);

                    ObjectOutputStream oos =
                            new ObjectOutputStream(fos)
            ) {

                for (VisitorRecords record : records) {
                    oos.writeObject(record);
                }
            }



            visitorRecordsTableView.setItems(records);


            Helper.showAlert(
                    "Success",
                    "Visitor record saved successfully."
            );


            clearFields();

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save visitor record."
            );
        }
    }


    private void clearFields() {

        visitorIdTextField.clear();
        visitorNameTextField.clear();
        prisonerNameTextField.clear();
        visitDateTextField.clear();
        statusTextField.clear();
        reviewCommentsTextArea.clear();
    }
}