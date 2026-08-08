package com.example.prison_management.Prisoner;

import com.example.prison_management.utils.BinaryFileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class PrisonerAddController {

    @FXML
    private TextField prisonerIdTF;
    @FXML
    private TextField prisonerNameTF;
    @FXML
    private ComboBox<String> threatLevelComboBox;
    @FXML
    private DatePicker releaseDateDP;
    @FXML
    private TextField cellNumberTF;

    @FXML
    public void initialize() {
        if (threatLevelComboBox != null) {
            threatLevelComboBox.getItems().addAll("Low", "Medium", "High", "Maximum");
        }
    }

    @FXML
    public void AddPrisonerOA(ActionEvent actionEvent) {
        String id = prisonerIdTF.getText().trim();
        String name = prisonerNameTF.getText().trim();
        String cell = cellNumberTF.getText().trim();
        LocalDate releaseDate = releaseDateDP.getValue();
        String threatLevel = threatLevelComboBox.getValue();

        Prisoner newPrisoner = new Prisoner(id, name, cell,threatLevel, releaseDate);

        File f = new File("Prisoner.bin");
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            if (f.exists() && f.length() > 0) {
                fos = new FileOutputStream(f, true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(newPrisoner);
            oos.close();

            prisonerIdTF.clear();
            prisonerNameTF.clear();
            cellNumberTF.clear();
            releaseDateDP.setValue(null);
            threatLevelComboBox.getSelectionModel().clearSelection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}