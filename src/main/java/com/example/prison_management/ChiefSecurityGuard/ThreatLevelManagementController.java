package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.Prisoner.Prisoner;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ThreatLevelManagementController
{
    @javafx.fxml.FXML
    private ComboBox<String> threatLevelComboBox;
    @javafx.fxml.FXML
    private TextField prisonerIDTextField;
    @javafx.fxml.FXML
    private Label ThreatWarningsLabel;
    @javafx.fxml.FXML
    private TableColumn<Prisoner, String> prisonerIDColumn;
    @javafx.fxml.FXML
    private TableColumn<Prisoner, String> prisonerNameColumn;
    @javafx.fxml.FXML
    private TableColumn<Prisoner, String> threatLevelTableColumn;
    @javafx.fxml.FXML
    private TableView<Prisoner> allInfoTableView;
    @javafx.fxml.FXML
    private TableColumn<Prisoner, LocalDate> releaseDateColumn;
    @javafx.fxml.FXML
    private TableColumn<Prisoner, String> cellNumberTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
        threatLevelComboBox.getItems().addAll(
                "Normal",
                "Medium Threat",
                "High Threat"
        );

        prisonerIDColumn.setCellValueFactory(
                new PropertyValueFactory<>("prisonerId")
        );

        prisonerNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("prisonerName")
        );

        cellNumberTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("cellNumber")
        );

        threatLevelTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("threatLevel")
        );

        releaseDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("releaseDate")
        );
    }

    @javafx.fxml.FXML
    public void updateThreatLevelButtonOA(ActionEvent actionEvent) {
        ThreatWarningsLabel.setText("");

        String prisonerID =
                prisonerIDTextField.getText().trim();

        String selectedThreat =
                threatLevelComboBox.getValue();


        // Validate Prisoner ID

        if (prisonerID.isEmpty()) {

            ThreatWarningsLabel.setText(
                    "Please enter Prisoner ID."
            );

            return;
        }


        // Validate threat level

        if (selectedThreat == null) {

            ThreatWarningsLabel.setText(
                    "Please select a threat level."
            );

            return;
        }


        File f = new File("Prisoner.bin");

        if (!f.exists()) {

            ThreatWarningsLabel.setText(
                    "Prisoner data file not found."
            );

            return;
        }


        ArrayList<Prisoner> prisoners =
                new ArrayList<>();

        boolean prisonerFound = false;


        try {

            FileInputStream fis =
                    new FileInputStream(f);

            ObjectInputStream ois =
                    new ObjectInputStream(fis);


            while (true) {

                try {

                    Prisoner prisoner =
                            (Prisoner) ois.readObject();


                    if (prisoner.getPrisonerId()
                            .equals(prisonerID)) {

                        prisoner.setThreatLevel(
                                selectedThreat
                        );

                        prisonerFound = true;
                    }


                    prisoners.add(prisoner);


                } catch (EOFException e) {

                    break;
                }
            }

            ois.close();


            // Prisoner ID verification

            if (!prisonerFound) {

                ThreatWarningsLabel.setText(
                        "Prisoner ID not found."
                );

                return;
            }


            // Rewrite the updated Prisoner objects

            FileOutputStream fos =
                    new FileOutputStream(f);

            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);


            for (Prisoner prisoner : prisoners) {

                oos.writeObject(prisoner);
            }

            oos.close();


            // Show successful message

            ThreatWarningsLabel.setText(
                    "Prisoner " + prisonerID +
                            " threat level updated to \n" +
                            selectedThreat + "."
            );


            // Clear fields

            prisonerIDTextField.clear();

            threatLevelComboBox
                    .getSelectionModel()
                    .clearSelection();


            // Refresh TableView

            allInfoTableView.getItems().clear();

            for (Prisoner prisoner : prisoners) {

                allInfoTableView.getItems().add(prisoner);
            }


        } catch (Exception e) {

            e.printStackTrace();

            ThreatWarningsLabel.setText(
                    "Error updating prisoner information."
            );
        }
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","DashBoard");
    }


    @javafx.fxml.FXML
    public void showAllatInfoButtonOA(ActionEvent actionEvent) {
        allInfoTableView.getItems().clear();

        File f = new File("Prisoner.bin");

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    Prisoner prisoner =
                            (Prisoner) ois.readObject();

                    allInfoTableView.getItems().add(prisoner);

                } catch (EOFException e) {

                    break;
                }
            }

            ois.close();

        } catch (Exception e) {

            e.printStackTrace();


        }
    }
}