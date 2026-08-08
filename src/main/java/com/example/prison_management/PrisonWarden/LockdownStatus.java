package com.example.prison_management.PrisonWarden;

import java.io.Serializable;

public class LockdownStatus implements Serializable {

    private boolean isLockdownActive;

    public LockdownStatus(boolean isLockdownActive) {
        this.isLockdownActive = isLockdownActive;
    }

    public boolean isLockdownActive() {
        return isLockdownActive;
    }

    public void setLockdownActive(boolean lockdownActive) {
        isLockdownActive = lockdownActive;
    }
}