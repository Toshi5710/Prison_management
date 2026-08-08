package com.example.prison_management.ChiefCook;

import com.example.prison_management.mainuser.MealSchedule;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class MealScheduleManagementViewController {

    @FXML private DatePicker ScheduleDateDP;
    @FXML private ComboBox<String> MealTypeCB;
    @FXML private TextField MenuItemsTF;
    @FXML private Label ScheduleSavedNotificationLabel;

    @FXML private TableView<MealSchedule> MealScheduleManagementTV;
    @FXML private TableColumn<MealSchedule, String> DateTC;
    @FXML private TableColumn<MealSchedule, String> MealTypeTC;
    @FXML private TableColumn<MealSchedule, String> MenuTC;
    @FXML private TableColumn<MealSchedule, Integer> ServingCountTC;

    private final ObservableList<MealSchedule> scheduleDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // dynamic meal selections
        if (MealTypeCB != null) {
            MealTypeCB.setItems(FXCollections.observableArrayList("BREAKFAST", "LUNCH", "DINNER"));
        }

        // map ui table to dynamic data object
        if (MealScheduleManagementTV != null) {
            DateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
            MealTypeTC.setCellValueFactory(new PropertyValueFactory<>("mealType"));
            MenuTC.setCellValueFactory(new PropertyValueFactory<>("blockId"));
            ServingCountTC.setCellValueFactory(new PropertyValueFactory<>("servingsCount"));

            MealScheduleManagementTV.setItems(scheduleDataList);
        }

        if (ScheduleSavedNotificationLabel != null) {
            ScheduleSavedNotificationLabel.setText("");
        }
    }

    @FXML
    public void FetchPrisonerRequirementsButtonOA(ActionEvent event) {
        if (ScheduleDateDP == null || ScheduleDateDP.getValue() == null) {
            if (ScheduleSavedNotificationLabel != null) {
                ScheduleSavedNotificationLabel.setText("Please select a date first.");
            }
            return;
        }
        if (ScheduleSavedNotificationLabel != null) {
            ScheduleSavedNotificationLabel.setText("Servings requirement loaded successfully.");
        }
    }

    @FXML
    public void SaveDailyScheduleButtonOA(ActionEvent event) {
        LocalDate dt = ScheduleDateDP.getValue();
        String meal = MealTypeCB.getValue();

        String menu = "";
        if (MenuItemsTF != null && MenuItemsTF.getText() != null) {
            menu = MenuItemsTF.getText().trim();
        }

        if (dt == null || meal == null || menu.isEmpty()) {
            if (ScheduleSavedNotificationLabel != null) {
                ScheduleSavedNotificationLabel.setText("Error: Missing inputs in fields.");
            }
            return;
        }

        String finalDate = dt.toString();

        // unique identity string setup using clock time
        String timeStr = String.valueOf(System.currentTimeMillis());
        String customId = "SCH-" + timeStr.substring(timeStr.length() - 4);
        int servings = 200;

        MealSchedule newSchedule = new MealSchedule(customId, finalDate, meal, menu, servings);

        // run basic model checks
        if (!newSchedule.verifyScheduleDetails()) {
            if (ScheduleSavedNotificationLabel != null) {
                ScheduleSavedNotificationLabel.setText("Error: Verification failed.");
            }
            return;
        }

        // append entry into observable list
        scheduleDataList.add(newSchedule);

        if (ScheduleSavedNotificationLabel != null) {
            ScheduleSavedNotificationLabel.setText("Meal schedule saved successfully.");
        }

        if (MenuItemsTF != null) MenuItemsTF.clear();
        if (MealTypeCB != null) MealTypeCB.setValue(null);
        if (ScheduleDateDP != null) ScheduleDateDP.setValue(null);
    }

    @FXML
    public void BackButtonOA(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}
