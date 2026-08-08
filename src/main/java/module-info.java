module com.example.prison_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    opens com.example.prison_management to javafx.fxml;
    exports com.example.prison_management;

    opens com.example.prison_management.MedicalDoctor to javafx.fxml;
    exports com.example.prison_management.MedicalDoctor;

    opens com.example.prison_management.ChiefSecurityGuard to javafx.fxml;
    exports com.example.prison_management.ChiefSecurityGuard;


    opens com.example.prison_management.mainuser to javafx.base;
    exports com.example.prison_management.mainuser;

    opens com.example.prison_management.dummy to javafx.fxml;
    exports com.example.prison_management.dummy;


}