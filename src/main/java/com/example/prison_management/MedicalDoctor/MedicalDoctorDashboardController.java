package com.example.prison_management.MedicalDoctor;


import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;

public class MedicalDoctorDashboardController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void medicineInventoryButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicineInventory.fxml","Medicine Inventory");
    }

    @javafx.fxml.FXML
    public void medicalHistoryButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/MedicalHistory.fxml","Examine Prisoner");
    }

    @javafx.fxml.FXML
    public void emergencyCaseButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/EmergencyCase.fxml","Emergency");


    }


    @javafx.fxml.FXML
    public void prescriptionManagementButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/PrescriptionManagement.fxml","Prescription management");
    }

    @javafx.fxml.FXML
    public void hospitalTransferButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/HospitalTransferRequest.fxml","Hospital transfer");
    }

    @javafx.fxml.FXML
    public void appointmentButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/AppointmentScheduler.fxml","Appointment");
    }

    @javafx.fxml.FXML
    public void healthReportGeneratorButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/HealthReportGenerator.fxml","Health Report Generator");
    }

    @javafx.fxml.FXML
    public void examinePrisonerButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/MedicalDoctor/PrisonerExamination.fxml","Examine Prisoner");

    }

    @javafx.fxml.FXML
    public void logOutButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent,"/com/example/prison_management/Login.fxml","Login");
    }
}