package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.SecurityCamera;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class SecuritySurveillanceController
{

    @javafx.fxml.FXML
    private ComboBox<String> cameraLocationComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> cameraStatusComboBox;
    @javafx.fxml.FXML
    private TableColumn<SecurityCamera,String> securityCameraTableColumn;
    @javafx.fxml.FXML
    private TableView<SecurityCamera> cameraTableView;
    @javafx.fxml.FXML
    private TableColumn<SecurityCamera,String> cameraLocationTableColumn;
    @javafx.fxml.FXML
    private Label showUpdateLabel;
    @javafx.fxml.FXML
    private TableColumn<SecurityCamera,String> cameraStatusTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> securityCameraComboBox;

    @javafx.fxml.FXML
    public void initialize() {

        cameraLocationComboBox.getItems().addAll(
                "Camera 1 - Block A",
                "Camera 2 - Block B",
                "Camera 3 - Main Gate",
                "Camera 4 - Back Gate"
        );

        securityCameraComboBox.getItems().addAll("Camera 1", "Camera 2", "Camera 3");
        cameraStatusComboBox.getItems().addAll("Connected","Disconnected");



        securityCameraTableColumn.setCellValueFactory(new PropertyValueFactory<>("cameraID"));

        cameraLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        cameraStatusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));





    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Dashboard");
    }



    @javafx.fxml.FXML
    public void showAllInfoButtonOA(ActionEvent actionEvent) {

        cameraTableView.getItems().clear();

        File f = new File("CameraStatus.bin");


        if (!f.exists()) {

            showUpdateLabel.setText(
                    "No camera information has been saved yet."
            );
            return;
        }


        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);


            while (true) {

                try {

                    SecurityCamera camera =
                            (SecurityCamera) ois.readObject();

                    cameraTableView.getItems().add(camera);

                } catch (EOFException e) {

                    break;
                }
            }


            ois.close();

            showUpdateLabel.setText(
                    "Camera information loaded successfully."
            );


        } catch (Exception e) {

            e.printStackTrace();

            showUpdateLabel.setText(
                    "Error loading camera information."
            );
        }



    }

    @javafx.fxml.FXML
    public void checkButtonOA(ActionEvent actionEvent) {

        showUpdateLabel.setText("");

        String cameraID = securityCameraComboBox.getValue();
        String location = cameraLocationComboBox.getValue();
        String status = cameraStatusComboBox.getValue();


        // Validate camera selection
        if (cameraID == null) {
            showUpdateLabel.setText(
                    "Please select a security camera."
            );
            return;
        }


        // Validate location
        if (location == null) {
            showUpdateLabel.setText(
                    "Please select the camera location."
            );
            return;
        }


        // Validate status
        if (status == null) {
            showUpdateLabel.setText(
                    "Please select the camera status."
            );
            return;
        }


        // Create SecurityCamera object
        SecurityCamera camera = new SecurityCamera(
                cameraID,
                location,
                status
        );


        // Save to binary file
        File f = new File("CameraStatus.bin");

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


            oos.writeObject(camera);
            oos.close();


            // Display result
            if (status.equals("Connected")) {

                showUpdateLabel.setText(
                        "Camera connected successfully."
                );

            } else {

                showUpdateLabel.setText(
                        "Camera is disconnected."
                );
            }



            securityCameraComboBox.getSelectionModel().clearSelection();
            cameraLocationComboBox.getSelectionModel().clearSelection();
            cameraStatusComboBox.getSelectionModel().clearSelection();


        } catch (Exception e) {

            e.printStackTrace();

            showUpdateLabel.setText(
                    "Error saving camera information."
            );
        }




    }
}