package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.AppointmentStatus;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class AppointmentStatusController {

    @javafx.fxml.FXML
    private TableColumn<AppointmentStatus, String> statusTableColumn;

    @javafx.fxml.FXML
    private TableColumn<AppointmentStatus, String> tokenTableColumn;

    @javafx.fxml.FXML
    private TextField appointmentTokenTextField;

    @javafx.fxml.FXML
    private TableColumn<AppointmentStatus, String> prisonerTableColumn;

    @javafx.fxml.FXML
    private TextField statusTextField;

    @javafx.fxml.FXML
    private TextField tokenTextField;

    @javafx.fxml.FXML
    private TableView<AppointmentStatus> appointmentStatusTableView;

    @javafx.fxml.FXML
    private TextField prisonerTextField;

    @javafx.fxml.FXML
    private TableColumn<AppointmentStatus, java.time.LocalDate> visitDateTableColumn;

    @javafx.fxml.FXML
    private TextField visitDateTextField;


    @javafx.fxml.FXML
    public void initialize() {

        tokenTableColumn.setCellValueFactory(new PropertyValueFactory<>("token"));
        prisonerTableColumn.setCellValueFactory(new PropertyValueFactory<>("prisoner"));
        visitDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    @javafx.fxml.FXML
    public void checkStatusButtonOA(ActionEvent actionEvent) {

        String searchToken =
                appointmentTokenTextField.getText().trim();

        if (searchToken.isEmpty()) {

            Helper.showAlert(
                    "Error",
                    "Please enter appointment token."
            );

            return;
        }

        appointmentStatusTableView.getItems().clear();

        File f = new File("AppointmentStatus.bin");

        if (!f.exists()) {

            Helper.showAlert(
                    "Error",
                    "No appointment status information found."
            );

            return;
        }

        boolean found = false;

        try {

            FileInputStream fis =
                    new FileInputStream(f);

            ObjectInputStream ois =
                    new ObjectInputStream(fis);

            while (true) {

                try {

                    AppointmentStatus a =
                            (AppointmentStatus) ois.readObject();

                    if (a.getToken().equals(searchToken)) {

                        appointmentStatusTableView
                                .getItems()
                                .add(a);

                        tokenTextField.setText(a.getToken());

                        prisonerTextField.setText(
                                a.getPrisoner()
                        );

                        visitDateTextField.setText(
                                a.getVisitDate().toString()
                        );

                        statusTextField.setText(
                                a.getStatus()
                        );

                        found = true;
                    }

                } catch (Exception e) {
                    break;
                }
            }

            ois.close();

            if (!found) {

                Helper.showAlert(
                        "Not Found",
                        "No appointment found with this token."
                );

                tokenTextField.clear();
                prisonerTextField.clear();
                visitDateTextField.clear();
                statusTextField.clear();
            }

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not read appointment status."
            );
        }
    }
}