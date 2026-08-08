package com.example.prison_management.mainuser;

import java.io.Serializable;

public class FoodInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemId;
    private String itemName;
    private int currentStock;
    private int minimumRequired;

    public FoodInventory() {}

    public FoodInventory(String itemId, String itemName, int currentStock, int minimumRequired) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.currentStock = currentStock;
        this.minimumRequired = minimumRequired;
    }


    public boolean isReplenishmentNeeded() {
        return this.currentStock < this.minimumRequired;
    }


    public void updateStock(int addedQty) {
        this.currentStock += addedQty;
    }


    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public int getMinimumRequired() { return minimumRequired; }
    public void setMinimumRequired(int minimumRequired) { this.minimumRequired = minimumRequired; }
}
