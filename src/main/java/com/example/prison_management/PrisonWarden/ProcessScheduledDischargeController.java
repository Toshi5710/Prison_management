package com.example.prison_management.PrisonWarden;

import com.example.prison_management.Prisoner.Prisoner;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class ProcessScheduledDischargeController {

    @FXML
    private TableView<Prisoner> ProcessScheduledDischargeTV;
    @FXML
    private TextField InputTargetPrisonerIDTF;
    @FXML
    private TableColumn<Prisoner, String> PrisonerNameTC;
    @FXML
    private TableColumn<Prisoner, Long> RemainingDaysTC;

    private Prisoner searchedPrisoner = null;

    @FXML
    public void initialize() {
        PrisonerNameTC.setCellValueFactory(new PropertyValueFactory<>("prisonerName"));
        RemainingDaysTC.setCellValueFactory(new PropertyValueFactory<>("remainingDays"));
    }

    @FXML
    public void SearchAndLoadOA(ActionEvent actionEvent) {
        String searchId = InputTargetPrisonerIDTF.getText().trim();

        if (searchId.isEmpty()) {
            return;
        }

        ProcessScheduledDischargeTV.getItems().clear();
        searchedPrisoner = null;

        File f = new File("Prisoner.bin");

        if (!f.exists() || f.length() == 0) {
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Prisoner p = (Prisoner) ois.readObject();
                    if (p.getPrisonerId().equalsIgnoreCase(searchId)) {
                        searchedPrisoner = p;
                        ProcessScheduledDischargeTV.getItems().add(p);
                        break;
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void DeleteRecordOA(ActionEvent actionEvent) {
        if (searchedPrisoner == null) {
            return;
        }


        if (searchedPrisoner.getRemainingDays() > 0) {
            return;
        }

        File f = new File("Prisoner.bin");
        ArrayList<Prisoner> remainingPrisoners = new ArrayList<>();


        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Prisoner p = (Prisoner) ois.readObject();
                    if (!p.getPrisonerId().equalsIgnoreCase(searchedPrisoner.getPrisonerId())) {
                        remainingPrisoners.add(p);
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(f); // Overwrite mode
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Prisoner p : remainingPrisoners) {
                oos.writeObject(p);
            }

            oos.close();


            ProcessScheduledDischargeTV.getItems().clear();
            InputTargetPrisonerIDTF.clear();
            searchedPrisoner = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BackOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/PrisonWarden/PrisonWardenDashBoard.fxml", "PrisonWardenDashBoard");
    }
}