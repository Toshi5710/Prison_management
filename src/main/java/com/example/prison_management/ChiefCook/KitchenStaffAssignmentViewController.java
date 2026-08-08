package com.example.prison_management.ChiefCook;

import com.example.prison_management.mainuser.KitchenStaffAssignment;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class KitchenStaffAssignmentViewController {

    @FXML private ComboBox<String> StaffMemberCB;
    @FXML private ComboBox<String> WorkShiftCB;
    @FXML private TextField AssignedDutyTF;
    @FXML private Label ScheduleUpdatedconfirmationLabel;

    @FXML private TableView<KitchenStaffAssignment> KitchenStaffAssignmentTV;
    @FXML private TableColumn<KitchenStaffAssignment, String> AssignmentIDTC;
    @FXML private TableColumn<KitchenStaffAssignment, String> StaffNameTC;
    @FXML private TableColumn<KitchenStaffAssignment, String> ShiftTC;
    @FXML private TableColumn<KitchenStaffAssignment, String> StatusTC;

    private final ObservableList<KitchenStaffAssignment> assignmentDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // load dropdowns
        if (StaffMemberCB != null) {
            StaffMemberCB.setPromptText("Select Staff Member");
            StaffMemberCB.setItems(FXCollections.observableArrayList("Cook John", "Cook Smith", "Assistant Davis"));
        }
        if (WorkShiftCB != null) {
            WorkShiftCB.setPromptText("Select Work Shift");
            WorkShiftCB.setItems(FXCollections.observableArrayList("MORNING", "EVENING", "NIGHT"));
        }

        // set table columns
        if (KitchenStaffAssignmentTV != null) {
            AssignmentIDTC.setCellValueFactory(new PropertyValueFactory<>("assignmentId"));
            StaffNameTC.setCellValueFactory(new PropertyValueFactory<>("staffId"));
            ShiftTC.setCellValueFactory(new PropertyValueFactory<>("shiftPattern"));
            StatusTC.setCellValueFactory(new PropertyValueFactory<>("assignedDuty"));

            KitchenStaffAssignmentTV.setItems(assignmentDataList);
        }

        if (ScheduleUpdatedconfirmationLabel != null) {
            ScheduleUpdatedconfirmationLabel.setText("");
        }

        // sample items
        assignmentDataList.add(new KitchenStaffAssignment("ASN-101", "Cook John", "MORNING", "Breakfast Prep"));
        assignmentDataList.add(new KitchenStaffAssignment("ASN-102", "Cook Smith", "EVENING", "Dinner Service"));
    }

    @FXML
    public void FetchStaffAvailabilityButtonOA(ActionEvent actionEvent) {
        String selectedStaff = StaffMemberCB.getValue();
        if (selectedStaff == null) {
            if (ScheduleUpdatedconfirmationLabel != null) {
                ScheduleUpdatedconfirmationLabel.setText("Please select a staff member first!");
            }
            return;
        }
        if (ScheduleUpdatedconfirmationLabel != null) {
            ScheduleUpdatedconfirmationLabel.setText(selectedStaff + " is free to be assigned.");
        }
    }

    @FXML
    public void AssignDutyButtonOA(ActionEvent actionEvent) {
        String staff = StaffMemberCB.getValue();
        String shift = WorkShiftCB.getValue();

        String duty = "";
        if (AssignedDutyTF != null && AssignedDutyTF.getText() != null) {
            duty = AssignedDutyTF.getText().trim();
        }

        if (staff == null || shift == null || duty.isEmpty()) {
            if (ScheduleUpdatedconfirmationLabel != null) {
                ScheduleUpdatedconfirmationLabel.setText("Error: Missing text fields or dropdown selections.");
            }
            return;
        }

        // simple id generation using time
        String timeStr = String.valueOf(System.currentTimeMillis());
        String sub = timeStr.substring(timeStr.length() - 3);
        String generatedId = "ASN-" + sub;

        // save to list
        KitchenStaffAssignment newAssignment = new KitchenStaffAssignment(generatedId, staff, shift, duty);
        assignmentDataList.add(newAssignment);

        if (ScheduleUpdatedconfirmationLabel != null) {
            ScheduleUpdatedconfirmationLabel.setText("Assigned duty to " + staff);
        }

        // clear inputs
        if (AssignedDutyTF != null) AssignedDutyTF.clear();
        if (StaffMemberCB != null) StaffMemberCB.setValue(null);
        if (WorkShiftCB != null) WorkShiftCB.setValue(null);
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}