package com.example.prison_management.ChiefCook;

import com.example.prison_management.mainuser.FoodInventory;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SupplyRequestManagementViewController {

    @FXML private ComboBox<String> ItemtoRequestCB;
    @FXML private TextField RequiredQuantityTF;
    @FXML private Label RequestConfirmationLabel;

    @FXML private TableView<FoodInventory> SupplyRequestManagementTV;
    @FXML private TableColumn<FoodInventory, String> lowStockItemsTC;
    @FXML private TableColumn<FoodInventory, Integer> SuggestedQuantitiesTC;

    private final ObservableList<FoodInventory> lowStockDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // drop down items
        if (ItemtoRequestCB != null) {
            ItemtoRequestCB.setPromptText("Select Item to Request");
            ItemtoRequestCB.setItems(FXCollections.observableArrayList("Basmati Rice", "Lentils (Dal)", "Soybean Oil", "Salt"));
        }

        if (RequestConfirmationLabel != null) {
            RequestConfirmationLabel.setText("");
        }

        // table setup
        if (SupplyRequestManagementTV != null) {
            lowStockItemsTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            SuggestedQuantitiesTC.setCellValueFactory(new PropertyValueFactory<>("minimumRequired"));

            SupplyRequestManagementTV.setItems(lowStockDataList);
        }

        // add default low stock items
        lowStockDataList.add(new FoodInventory("ITEM-002", "Lentils (Dal)", 20, 100));
        lowStockDataList.add(new FoodInventory("ITEM-003", "Soybean Oil", 10, 50));
    }

    @FXML
    public void ReviewCurrentInventoryButtonOA(ActionEvent actionEvent) {
        if (RequestConfirmationLabel != null) {
            RequestConfirmationLabel.setText("Inventory check complete.");
        }
    }

    @FXML
    public void SubmitSupplyRequestButtonOA(ActionEvent actionEvent) {
        String item = ItemtoRequestCB.getValue();

        String qtyText = "";
        if (RequiredQuantityTF != null && RequiredQuantityTF.getText() != null) {
            qtyText = RequiredQuantityTF.getText().trim();
        }

        if (item == null || qtyText.isEmpty()) {
            if (RequestConfirmationLabel != null) {
                RequestConfirmationLabel.setText("Error: Fill all fields first.");
            }
            return;
        }

        try {
            int amt = Integer.parseInt(qtyText);

            if (amt <= 0) {
                if (RequestConfirmationLabel != null) {
                    RequestConfirmationLabel.setText("Error: Quantity must be positive.");
                }
                return;
            }

            if (RequestConfirmationLabel != null) {
                RequestConfirmationLabel.setText("Request sent for " + amt + " units of " + item);
            }

            // Student loop style to find and remove matched item instead of using lambda removeIf
            for (int i = 0; i < lowStockDataList.size(); i++) {
                if (lowStockDataList.get(i).getItemName().equalsIgnoreCase(item)) {
                    lowStockDataList.remove(i);
                    break;
                }
            }

            // clear fields
            if (RequiredQuantityTF != null) RequiredQuantityTF.clear();
            if (ItemtoRequestCB != null) ItemtoRequestCB.setValue(null);

        } catch (NumberFormatException e) {
            if (RequestConfirmationLabel != null) {
                RequestConfirmationLabel.setText("Error: Enter a valid number.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}