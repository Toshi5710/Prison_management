package com.example.prison_management.mainuser;

import java.io.Serializable;

public class CancelAppointment implements Serializable {

    private String token;

    public CancelAppointment(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CancelAppointment{" +
                "token='" + token + '\'' +
                '}';
    }
}