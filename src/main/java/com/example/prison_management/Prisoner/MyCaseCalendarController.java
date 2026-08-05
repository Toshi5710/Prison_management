package com.example.prison_management.Prisoner;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyCaseCalendarController
{
    @javafx.fxml.FXML
    private TableView MyCaseCalendarTV;
    @javafx.fxml.FXML
    private TableColumn TimesTC;
    @javafx.fxml.FXML
    private TableColumn UpcomingHearingDatesTC;
    @javafx.fxml.FXML
    private TableColumn CourtRoomsTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void LoadScheduleOA(ActionEvent actionEvent) {
    }
}