package com.example.prison_management;

import com.example.prison_management.utils.Helper;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginController
{
    @javafx.fxml.FXML
    private ComboBox<String> UserRoleComboBox;
    @javafx.fxml.FXML
    private TextField UserIDTF;
    @javafx.fxml.FXML
    private TextField PasswordTF;

    @javafx.fxml.FXML
    public void initialize() {
        UserRoleComboBox.getItems().addAll(
                "Prison Warden",
                "Deputy Warden",
                "Chief Security Guard",
                "Medical Doctor",
                "Prison Accountant",
                "Chief cook",
                "Visitor",
                "Prisoner"
        );

        UserRoleComboBox.setPromptText("Select User Type");



    }

    @javafx.fxml.FXML
    public void SignINOA(ActionEvent actionEvent) {

        boolean loginSuccess = false;

        String UserRole = UserRoleComboBox.getValue();
        String userID = UserIDTF.getText().trim();
        String password = PasswordTF.getText().trim();

        if (UserRole == null) {
            Helper.showErrorAlert("Please select a role.");
            return;
        }

        if (userID.isEmpty()) {
            Helper.showInformationAlert("Error", "Please enter your User ID.");
            return;
        }

        // Check if Password is empty
        if (password.isEmpty()) {
            Helper.showInformationAlert("Error", "Please enter your Password.");
            return;
        }




        switch (UserRole) {



            case "Prison Warden":

                if (userID.equals("1234") && password.equals("abc")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/MedicalDoctor/MedicalDashboard.fxml",
                            "Medical Dashboard"
                    );
                    return;
                }
                break;

            case "Deputy Warden":

                if (userID.equals("2345") && password.equals("def")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/ChiefSecurityOfficer/ChiefSecurityDashboard.fxml",
                            "Chief Security Dashboard"
                    );
                    return;
                }
                break;

            case "Chief Security Guard":

                if (userID.equals("3456") && password.equals("ghi")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/ChiefSecurityGuard/ChiefSecurityDashboard.fxml",
                            "Chief Security Guard"
                    );
                    return;
                }
                break;

            case "Medical Doctor":

                if (userID.equals("4567") && password.equals("jkl")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/MedicalDoctor/MedicalDashboard.fxml",
                            "Medical Doctor"
                    );

                    return;
                }
                break;

            case "Prison Accountant":

                if (userID.equals("5678") && password.equals("mno")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/Prisoner/PrisonerDashboard.fxml",
                            "Prisoner Dashboard"
                    );
                    return;
                }
                break;

            case "Chief cook":

                if (userID.equals("6789") && password.equals("pqr")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/ChiefAccountant/ChiefAccountantDashboard.fxml",
                            "Chief Accountant Dashboard"
                    );
                    return;
                }
                break;

            case "Visitor":

                if (userID.equals("7890") && password.equals("stu")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/Receptionist/ReceptionistDashboard.fxml",
                            "Receptionist Dashboard"
                    );
                    return;
                }
                break;

            case "Prisoner":

                if (userID.equals("8901") && password.equals("vwx")) {


                    SceneSwitcher.switchScene(
                            actionEvent,
                            "/MedicalAssistant/MedicalAssistantDashboard.fxml",
                            "Medical Assistant Dashboard"
                    );

                    return;
                }
                break;
        }

        Helper.showInformationAlert(
                "Login Failed",
                "The User ID or Password does not match the selected role.\nPlease try again."
        );
    }
}