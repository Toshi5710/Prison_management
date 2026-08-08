package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.DailyActivity;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class DailyActivitiesController {

    @javafx.fxml.FXML
    private TextField guardReportTextField;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> prisonerNameTableColumn;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> guardReportTableColumn;

    @javafx.fxml.FXML
    private TextField activityTextField;

    @javafx.fxml.FXML
    private TextField movementTextField;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> prisonerIdTableColumn;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> activityTableColumn;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> statusTableColumn;

    @javafx.fxml.FXML
    private TextField prisonerNameTextField;

    @javafx.fxml.FXML
    private TableColumn<DailyActivity, String> movementTableColumn;

    @javafx.fxml.FXML
    private TableView<DailyActivity> dailyActivitiesTableView;

    @javafx.fxml.FXML
    private TextField prisonerIdTextField;

    @javafx.fxml.FXML
    private TextField statusTextField;


    @javafx.fxml.FXML
    public void initialize() {

        prisonerIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
        prisonerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("prisonerName"));
        activityTableColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));
        movementTableColumn.setCellValueFactory(new PropertyValueFactory<>("movement"));
        guardReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("guardReport"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        dailyActivitiesTableView.getItems().clear();

        File file = new File("DailyActivity.bin");

        if (!file.exists()) {
            Helper.showAlert("Information",
                    "No daily activity records found.");
            return;
        }

        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            while (true) {

                try {

                    DailyActivity activity =
                            (DailyActivity) ois.readObject();

                    dailyActivitiesTableView.getItems().add(activity);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not load daily activities."
            );
        }
    }


    @javafx.fxml.FXML
    public void viewDetailsButtonOA(ActionEvent actionEvent) {

        DailyActivity selectedActivity =
                dailyActivitiesTableView.getSelectionModel().getSelectedItem();

        if (selectedActivity == null) {

            Helper.showAlert(
                    "Warning",
                    "Please select an activity first."
            );

            return;
        }


        prisonerIdTextField.setText(selectedActivity.getPrisonerId());
        prisonerNameTextField.setText(selectedActivity.getPrisonerName());
        activityTextField.setText(selectedActivity.getActivity());
        movementTextField.setText(selectedActivity.getMovement());
        guardReportTextField.setText(selectedActivity.getGuardReport());
        statusTextField.setText(selectedActivity.getStatus());
    }
}