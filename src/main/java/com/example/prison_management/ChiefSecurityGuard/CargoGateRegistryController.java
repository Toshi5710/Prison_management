package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.VehicleEntry;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.BinaryRefAddr;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CargoGateRegistryController
{
    @javafx.fxml.FXML
    private TextField driverNameTextField;
    @javafx.fxml.FXML
    private DatePicker entryDatePicker;
    @javafx.fxml.FXML
    private TextField timeTextField;
    @javafx.fxml.FXML
    private TextField licensePlateTextField;
    @javafx.fxml.FXML
    private ComboBox<String> accessEntryComboBox;
    @javafx.fxml.FXML
    private TableColumn<VehicleEntry, LocalDate> entryDateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<VehicleEntry,String> driverNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<VehicleEntry,String> licensePlateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<VehicleEntry,Integer> timeTableColumn;
    @javafx.fxml.FXML
    private TableColumn<VehicleEntry, String> accessEntryTableColumn;
    @javafx.fxml.FXML
    private TableView<VehicleEntry> informatioTableView;
    @javafx.fxml.FXML
    private Label messageLabel;


    @javafx.fxml.FXML
    public void initialize() {

        accessEntryComboBox.getItems().addAll("Yes","No");

        licensePlateTableColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        driverNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        entryDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        accessEntryTableColumn.setCellValueFactory(new PropertyValueFactory<>("accessEntry"));


    }

    @javafx.fxml.FXML
    public void authorizeEntryInformationButtonOA(ActionEvent actionEvent) {

        String license = licensePlateTextField.getText().trim();

        if (!license.matches("[A-Z]{3}-\\d{4}")) {
            messageLabel.setText("License plate must be in the format ABC-1234");
            return;
        }



        VehicleEntry v = new VehicleEntry(
                licensePlateTextField.getText(),
                driverNameTextField.getText(),
                entryDatePicker.getValue(),
                Integer.parseInt(timeTextField.getText()),
                accessEntryComboBox.getValue()
        );


        File f = new File("VehicleEntry.bin");

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {

            if (f.exists()){
                fos = new FileOutputStream(f, true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);
            }

            else {

                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(v);
            messageLabel.setText("Entry registered successfully !");

            licensePlateTextField.clear();
            driverNameTextField.clear();
            entryDatePicker.setValue(null);
            timeTextField.clear();
            accessEntryComboBox.getSelectionModel().clearSelection();



            oos.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }





    }

    @Deprecated

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(actionEvent, "/ChiefSecurityGuard/ChiefSecurityDashboard.fxml", "Dashboard");
    }

    @javafx.fxml.FXML
    public void loadOnTableButtonOA(ActionEvent actionEvent) {

        informatioTableView.getItems().clear();


        File f = new File("VehicleEntry.bin");

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);


            while (true){

                try {
                    VehicleEntry a = (VehicleEntry) ois.readObject();
                    informatioTableView.getItems().add(a);
                }
                catch (EOFException e){

                    break;

                }


            }

            ois.close();



        }
        catch (Exception e){
            e.printStackTrace();
        }




    }
}