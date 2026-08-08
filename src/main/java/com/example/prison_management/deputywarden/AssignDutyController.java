package com.example.prison_management.deputywarden;

import com.example.prison_management.mainuser.AssignDuty;
import com.example.prison_management.utils.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AssignDutyController {

    @javafx.fxml.FXML
    private ComboBox<String> dutyAreaComboBox;

    @javafx.fxml.FXML
    private ComboBox<String> guardComboBox;

    @javafx.fxml.FXML
    private DatePicker datePicker;

    @javafx.fxml.FXML
    private ComboBox<String> shiftComboBox;

    @javafx.fxml.FXML
    public void initialize() {

        guardComboBox.getItems().addAll("Guard 1", "Guard 2", "Guard 3");
        shiftComboBox.getItems().addAll("Morning", "Afternoon", "Night");
        dutyAreaComboBox.getItems().addAll("Cell Block", "Main Gate", "Yard", "Visitor Area");
    }

    @javafx.fxml.FXML
    public void assignButtonOA(ActionEvent actionEvent) {

        if (guardComboBox.getValue() == null ||
                shiftComboBox.getValue() == null ||
                dutyAreaComboBox.getValue() == null ||
                datePicker.getValue() == null) {

            Helper.showAlert(
                    "Error",
                    "Please fill up all the fields."
            );

            return;
        }

        AssignDuty a = new AssignDuty(
                dutyAreaComboBox.getValue().toString(),
                guardComboBox.getValue().toString(),
                datePicker.getValue(),
                shiftComboBox.getValue().toString()
        );

        File f = new File("AssignDuty.bin");

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

            oos.writeObject(a);

            oos.close();

            Helper.showAlert(
                    "Success",
                    "Duty assigned successfully."
            );

            clearButtonOA(actionEvent);

        } catch (Exception e) {

            e.printStackTrace();

            Helper.showAlert(
                    "Error",
                    "Could not save duty information."
            );
        }
    }

    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {

        guardComboBox.getSelectionModel().clearSelection();
        shiftComboBox.getSelectionModel().clearSelection();
        dutyAreaComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
    }
}