package com.example.prison_management.ChiefCook;

import com.example.prison_management.mainuser.FoodInventory;
import com.example.prison_management.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FoodInventoryViewController {

    @FXML private TableView<FoodInventory> FoodInventoryTV;
    @FXML private TableColumn<FoodInventory, String> ItemIDTC;
    @FXML private TableColumn<FoodInventory, String> ItemNameTC;
    @FXML private TableColumn<FoodInventory, Integer> CurrentStockTC;
    @FXML private TableColumn<FoodInventory, Integer> MinimumRequiredStockTC;

    @FXML private ComboBox<String> FoodItemIDCB;
    @FXML private TextField StockQuantitytoAddOrRemoveTF;
    @FXML private Label InventoryStatusupdateLabel;

    private final ObservableList<FoodInventory> inventoryData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // combobox items
        if (FoodItemIDCB != null) {
            FoodItemIDCB.setPromptText("Select Food Item");
            FoodItemIDCB.setItems(FXCollections.observableArrayList("Basmati Rice", "Lentils (Dal)", "Soybean Oil"));
        }

        // map columns
        if (FoodInventoryTV != null) {
            ItemIDTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            ItemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            CurrentStockTC.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
            MinimumRequiredStockTC.setCellValueFactory(new PropertyValueFactory<>("minimumRequired"));

            FoodInventoryTV.setItems(inventoryData);
        }

        // add starting values
        inventoryData.add(new FoodInventory("ITEM-001", "Basmati Rice", 500, 200));
        inventoryData.add(new FoodInventory("ITEM-002", "Lentils (Dal)", 120, 100));
        inventoryData.add(new FoodInventory("ITEM-003", "Soybean Oil", 80, 50));

        if (InventoryStatusupdateLabel != null) {
            InventoryStatusupdateLabel.setText("");
        }
    }

    @FXML
    public void FetchFoodStockButtonOA(ActionEvent actionEvent) {
        String selectedName = FoodItemIDCB.getValue();
        if (selectedName == null) {
            if (InventoryStatusupdateLabel != null) {
                InventoryStatusupdateLabel.setText("Please select an item first!");
            }
            return;
        }

        if (InventoryStatusupdateLabel != null) {
            InventoryStatusupdateLabel.setText("Fetched data for: " + selectedName);
        }
    }

    @FXML
    public void StockQuantitytoAddOrRemoveTF(ActionEvent actionEvent) {
        // Left blank or simple return to prevent crashing if triggered accidentally by fxml
    }

    @FXML
    public void UpdateInventoryButtonOA(ActionEvent actionEvent) {
        String selectedName = FoodItemIDCB.getValue();

        if (selectedName == null) {
            if (InventoryStatusupdateLabel != null) {
                InventoryStatusupdateLabel.setText("Error: Select an item name first!");
            }
            return;
        }

        if (StockQuantitytoAddOrRemoveTF == null || StockQuantitytoAddOrRemoveTF.getText().trim().isEmpty()) {
            if (InventoryStatusupdateLabel != null) {
                InventoryStatusupdateLabel.setText("Error: Enter a quantity value!");
            }
            return;
        }

        try {
            int amt = Integer.parseInt(StockQuantitytoAddOrRemoveTF.getText().trim());
            boolean found = false;

            for (FoodInventory item : inventoryData) {
                if (item.getItemName().equalsIgnoreCase(selectedName)) {

                    item.updateStock(amt);

                    // check if stock dropped below zero
                    if (item.getCurrentStock() < 0) {
                        if (InventoryStatusupdateLabel != null) {
                            InventoryStatusupdateLabel.setText("Error: Stock cannot be less than 0.");
                        }
                        // rollback changes
                        item.setCurrentStock(item.getCurrentStock() - amt);
                        return;
                    }

                    found = true;
                    break;
                }
            }

            if (FoodInventoryTV != null) {
                FoodInventoryTV.refresh();
            }

            if (InventoryStatusupdateLabel != null) {
                if (found) {
                    InventoryStatusupdateLabel.setText("Stock updated for " + selectedName + " by " + amt + " units.");
                } else {
                    InventoryStatusupdateLabel.setText("Inventory stock updated successfully.");
                }
            }

        } catch (NumberFormatException e) {
            if (InventoryStatusupdateLabel != null) {
                InventoryStatusupdateLabel.setText("Error: Please enter a valid whole number.");
            }
        }
    }

    @FXML
    public void BackButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchScene(actionEvent, "/ChiefCook/ChiefCookDashboard.fxml", "Chief Cook Dashboard");
    }
}