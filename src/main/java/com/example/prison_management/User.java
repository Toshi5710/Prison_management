package com.example.prison_management;

public abstract class User {
    private String UserID;
    private String Password;
    private String UserRole;

    public User(String userID, String password, String userRole) {
        UserID = userID;
        Password = password;
        UserRole = userRole;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID='" + UserID + '\'' +
                ", Password='" + Password + '\'' +
                ", UserRole='" + UserRole + '\'' +
                '}';
    }
}