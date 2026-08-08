package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.VisitorScreeningLog;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class VisitorScreeningController
{
    @javafx.fxml.FXML
    private TextField visitorIDTextField;
    @javafx.fxml.FXML
    private DatePicker visitingDateDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> purposeComboBox;
    @javafx.fxml.FXML
    private Label showSuccessConfirmationLabel;
    @javafx.fxml.FXML
    private TextField visitorNameTextField;
    @javafx.fxml.FXML
    private ComboBox<String> permissionComboBox;

    @javafx.fxml.FXML
    public void initialize() {

        purposeComboBox.getItems().addAll("Family Reason","Official Reason","Not valid reason");
        permissionComboBox.getItems().addAll("Entry Granted","Entry Denied","Pending");


    }


    @javafx.fxml.FXML
    public void saveInfoButtonOA(ActionEvent actionEvent) {

        showSuccessConfirmationLabel.setText("");

        String visitorID = visitorIDTextField.getText().trim();

        if (!visitorID.matches("\\d{5}")) {
            showSuccessConfirmationLabel.setText("Visitor ID must be exactly 5 digits.");
            return;
        }


        //String visitorID, String visitorName, String purpose, String permission, LocalDate visitingDate

        VisitorScreeningLog v = new VisitorScreeningLog(
                visitorIDTextField.getText(),
                visitorNameTextField.getText(),
                purposeComboBox.getValue(),
                permissionComboBox.getValue(),
                visitingDateDatePicker.getValue()

        );


        File f =  new File("VisitorScreening.bin");

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            if (f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);

            }
            else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);

            }

            oos.writeObject(v);

            String permission = (String) permissionComboBox.getValue();

            if (permission.equals("Entry Granted")) {
                showSuccessConfirmationLabel.setText("Entry granted successfully!");
            }
            else if (permission.equals("Entry Denied")) {
                showSuccessConfirmationLabel.setText("Entry denied!");
            }
            else if (permission.equals("Pending")) {
                showSuccessConfirmationLabel.setText("Entry is pending!");
            }


            visitorIDTextField.clear();
            visitorNameTextField.clear();
            purposeComboBox.getSelectionModel().clearSelection();
            permissionComboBox.getSelectionModel().clearSelection();
            visitingDateDatePicker.setValue(null);



        }
        catch (Exception e){
            e.printStackTrace();

        }



    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Dashboard");

    }


}