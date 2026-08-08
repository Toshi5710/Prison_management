package com.example.prison_management.utils;

import javafx.scene.control.Alert;

public class Helper {

    public static void showErrorAlert(String s){
        Alert alert  = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(s);
        alert.showAndWait();

    }

    public static void showInformationAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
