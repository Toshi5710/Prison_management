package com.example.prison_management.mainuser;

import java.io.Serializable;

public class SecurityCamera implements Serializable {

    private String cameraID;
    private String location;
    private String status;

    public SecurityCamera(String cameraID, String location, String status) {
        this.cameraID = cameraID;
        this.location = location;
        this.status = status;
    }

    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecurityCamera{" +
                "cameraID='" + cameraID + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
