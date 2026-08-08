package com.example.prison_management.visitor;

import com.example.prison_management.mainuser.VisitRules;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class VisitRulesController {

    @javafx.fxml.FXML
    private TextArea rulesTextArea;

    @javafx.fxml.FXML
    public void initialize() {

        rulesTextArea.setText(
                "1. Visitor must bring valid ID.\n" +
                        "2. Visitor must follow prison security rules.\n" +
                        "3. Visitor must arrive on time.\n" +
                        "4. Mobile phones may not be allowed inside.\n" +
                        "5. Visitor must follow staff instructions."
        );
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchScene(
                actionEvent,
                "/visitor/VisitorDashboard.fxml",
                "Visitor Dashboard"
        );
    }

    @javafx.fxml.FXML
    public void downloadPDFButtonOA(ActionEvent actionEvent) {

        String rules = rulesTextArea.getText();

        if (rules.isEmpty()) {
            return;
        }

        try {

            VisitRules v = new VisitRules(rules);

            File f = new File("VisitRules.bin");

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(v);

            oos.close();

            System.out.println("Visit rules saved successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}