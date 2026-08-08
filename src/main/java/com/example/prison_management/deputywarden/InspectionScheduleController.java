package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.InspectionSchedule;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class InspectionScheduleController {

    @javafx.fxml.FXML
    private DatePicker datePicker;

    @javafx.fxml.FXML
    private ComboBox<String> blockComboBox;

    @javafx.fxml.FXML
    private ComboBox<String> inspectionTeamComboBox;


    @javafx.fxml.FXML
    public void initialize() {

        blockComboBox.getItems().addAll("Block A", "Block B", "Block C", "Block D");
        inspectionTeamComboBox.getItems().addAll("Team 1", "Team 2", "Team 3");
    }


    @javafx.fxml.FXML
    public void scheduleButtonOA(ActionEvent actionEvent) {

        if (datePicker.getValue() == null || blockComboBox.getValue() == null || inspectionTeamComboBox.getValue() == null) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }


        InspectionSchedule i = new InspectionSchedule(
                datePicker.getValue(),
                blockComboBox.getValue(),
                inspectionTeamComboBox.getValue()
        );


        File f = new File("InspectionSchedule.bin");

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


            oos.writeObject(i);

            oos.close();


            Helper.showAlert(
                    "Success",
                    "Inspection scheduled successfully."
            );


            clearButtonOA(actionEvent);


        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save inspection schedule."
            );
        }
    }


    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {

        datePicker.setValue(null);

        blockComboBox.getSelectionModel().clearSelection();

        inspectionTeamComboBox.getSelectionModel().clearSelection();
    }
}