package com.example.prison_management.Prisoner;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CourseEnrollmentController
{
    @javafx.fxml.FXML
    private TextField EnrollByCourseIDTF;
    @javafx.fxml.FXML
    private TableView CourseEnrollmentTV;
    @javafx.fxml.FXML
    private TableColumn CourseIdTC;
    @javafx.fxml.FXML
    private TableColumn CourseNameTC;
    @javafx.fxml.FXML
    private TableColumn SeatsAvailableTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void EnrollNowOA(ActionEvent actionEvent) {
    }
}