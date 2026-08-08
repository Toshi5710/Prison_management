package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.conductsHeadCount;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PrisonerHeadCountController
{
    @javafx.fxml.FXML
    private TextField blockNumnerTextField;
    @javafx.fxml.FXML
    private Label showOutputLabel;
    @javafx.fxml.FXML
    private ComboBox<String> shiftComboBox;
    @javafx.fxml.FXML
    private TextField prisonerCountTextField;
    @javafx.fxml.FXML
    private DatePicker dateDatePicker;

    @javafx.fxml.FXML
    public void initialize() {

        shiftComboBox.getItems().addAll(
                "Morning",
                "Afternoon",
                "Night"
        );


    }

    @javafx.fxml.FXML
    public void takeHeadCountButtonOA(ActionEvent actionEvent) {


        showOutputLabel.setText("");

        String blockText = blockNumnerTextField.getText().trim();;
        String shift = shiftComboBox.getValue();
        int prisonerCount;



        if (!blockText.matches("\\d+")) {
            showOutputLabel.setText(
                    "Block number must be a valid number."
            );
            return;
        }

        int blockNumber = Integer.parseInt(blockText);



        if (shift == null) {
            showOutputLabel.setText(
                    "Please select a shift."
            );
            return;
        }



        if (dateDatePicker.getValue() == null) {
            showOutputLabel.setText(
                    "Please select a date."
            );
            return;
        }



        String countText = prisonerCountTextField.getText().trim();

        if (!countText.matches("\\d+")) {
            showOutputLabel.setText(
                    "Prisoner count must be a valid number."
            );
            return;
        }

        prisonerCount = Integer.parseInt(countText);



        //int blockNumber, int prisonerCount, String shift, LocalDate date

        conductsHeadCount h = new conductsHeadCount(
                blockNumber,
                shift,
                prisonerCount,
                dateDatePicker.getValue()

        );



        File f = new File("Headcount.bin");

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

            oos.writeObject(h);
            oos.close();


            // Check prisoner count
            if (prisonerCount < 20) {

                showOutputLabel.setText(
                        "Warning:\n" +
                                "Only " + prisonerCount +
                                " prisoners counted.\n" + "Please report to the authority!"
                );

            } else {

                showOutputLabel.setText(
                        "Headcount completed successfully!\n " +
                                "Total prisoners: " + prisonerCount
                );
            }



            blockNumnerTextField.clear();
            shiftComboBox.getSelectionModel().clearSelection();
            prisonerCountTextField.clear();
            dateDatePicker.setValue(null);


        } catch (Exception e) {

            e.printStackTrace();

                    }
    }



    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Dashboard");
    }
}