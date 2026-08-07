package com.example.prison_management.ChiefSecurityGuard;

import com.example.prison_management.mainuser.InspectionReport;
import com.example.prison_management.utils.BinaryFileUtil;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.beans.binding.ObjectExpression;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class CellInspectionController
{
    @javafx.fxml.FXML
    private DatePicker inspectionDateDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> reportStatusComboBox;
    @javafx.fxml.FXML
    private TextField reportIDTextField;
    @javafx.fxml.FXML
    private TableView<InspectionReport> detailsTableView;
    @javafx.fxml.FXML
    private TableColumn<InspectionReport, LocalDate> inspectionDateTableColumn;
    @javafx.fxml.FXML
    private TextField remarksTextField;
    @javafx.fxml.FXML
    private TableColumn<InspectionReport, String> remarksTableColumn;
    @javafx.fxml.FXML
    private Label showSuccessMessageLabel;
    @javafx.fxml.FXML
    private TableColumn<InspectionReport, String> cellNumberTableColumn;
    @javafx.fxml.FXML
    private TableColumn<InspectionReport, Integer> reportIDTableColumn;
    @javafx.fxml.FXML
    private TableColumn<InspectionReport, String> reportStatusTableColumn;
    @javafx.fxml.FXML
    private TextField cellNumberTextField;

    @javafx.fxml.FXML
    public void initialize() {

        //String reportID, int cellID, LocalDate inspectionDate, String status, String remarks


        reportStatusComboBox.getItems().addAll("Good", "Bad");

        reportIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        cellNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("cellID"));
        inspectionDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("inspectionDate"));
        reportStatusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        remarksTableColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));





    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefSecurityGuard/ChiefSecurityDashboard.fxml","Dashboard");
    }

    @javafx.fxml.FXML
    public void saveReportButtonOA(ActionEvent actionEvent) {


        //String reportID, int cellID, LocalDate inspectionDate, String status, String remarks

        InspectionReport i = new InspectionReport(
                reportIDTextField.getText(),
                Integer.parseInt(cellNumberTextField.getText()),
                inspectionDateDatePicker.getValue(),
                reportStatusComboBox.getValue(),
                remarksTextField.getText()
        );

        File f = new File("InspectionReport.bin");

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            if (f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);

            }
            else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(i);
            showSuccessMessageLabel.setText("information saved successfully! ");

            cellNumberTextField.clear();
            reportIDTextField.clear();
            inspectionDateDatePicker.setValue(null);
            reportStatusComboBox.getSelectionModel().clearSelection();
            remarksTextField.clear();

            oos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    public void showAllDetailsOnTableButtonOA(ActionEvent actionEvent) {
        detailsTableView.getItems().clear();

        File f = new File("InspectionReport.bin");

        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true){
                try {
                    InspectionReport a = (InspectionReport) ois.readObject();
                    detailsTableView.getItems().add(a);
                }catch (Exception e){
                    break;
                }


        }
            ois.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}