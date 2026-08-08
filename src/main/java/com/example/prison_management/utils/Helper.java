package com.example.prison_management.utils;

import javafx.scene.control.Alert;

public class Helper {

    public static void showAlert(String s){
        Alert a  = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();

    }

    public static void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
