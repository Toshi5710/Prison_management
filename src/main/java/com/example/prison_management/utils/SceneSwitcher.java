package com.example.prison_management.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    // switch entire scene/window
    public static void switchScene(ActionEvent actionEvent, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load fxml inside an anchorpane
    public static void switchView(AnchorPane mainPane, String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));

            mainPane.getChildren().setAll(view);

            if (mainPane.getScene() != null) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.sizeToScene();
            }

        } catch (IOException e) {
            System.err.println("Failed to load view: " + fxmlPath);
            e.printStackTrace();
        }
    }

    // load fxml inside a borderpane center
    public static void switchView(BorderPane mainPane, String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));

            mainPane.setCenter(view);

            if (mainPane.getScene() != null) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.sizeToScene();
            }

        } catch (IOException e) {
            System.err.println("Failed to load view: " + fxmlPath);
            e.printStackTrace();
        }
    }
}