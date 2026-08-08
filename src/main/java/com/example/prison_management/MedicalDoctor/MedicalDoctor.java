package com.example.prison_management.MedicalDoctor;

import com.example.prison_management.User;

import java.io.Serializable;

public class MedicalDoctor  implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String doctorID;

    private String specialization;

    private final String licenseNumber;

    private String department;

    private String contactNumber;


    public MedicalDoctor(String doctorID, String licenseNumber, String specialization, String department, String contactNumber) {
        this.doctorID = doctorID;
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
        this.department = department;
        this.contactNumber = contactNumber;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "MedicalDoctor{" +
                "doctorID='" + doctorID + '\'' +
                ", specialization='" + specialization + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", department='" + department + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
