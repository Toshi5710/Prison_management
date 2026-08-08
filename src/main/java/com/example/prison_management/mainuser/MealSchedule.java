package com.example.prison_management.mainuser;

import java.io.Serializable;

public class MealSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scheduleId;
    private String date; // Format: YYYY-MM-DD
    private String mealType; // "BREAKFAST", "LUNCH", "DINNER"
    private String blockId; // For meal distribution
    private int servingsCount;

    public MealSchedule() {}

    public MealSchedule(String scheduleId, String date, String mealType, String blockId, int servingsCount) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.mealType = mealType;
        this.blockId = blockId;
        this.servingsCount = servingsCount;
    }


    public boolean verifyScheduleDetails() {
        return servingsCount > 0 && date != null && !date.isEmpty();
    }


    public String getScheduleId() { return scheduleId; }
    public void setScheduleId(String scheduleId) { this.scheduleId = scheduleId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public String getBlockId() { return blockId; }
    public void setBlockId(String blockId) { this.blockId = blockId; }

    public int getServingsCount() { return servingsCount; }
    public void setServingsCount(int servingsCount) { this.servingsCount = servingsCount; }
}