module com.example.prison_management {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.prison_management to javafx.fxml;
    opens com.example.prison_management.ChiefCook to javafx.fxml;
    opens com.example.prison_management.PrisonAccountant to javafx.fxml;

    exports com.example.prison_management;
    exports com.example.prison_management.ChiefCook;
    exports com.example.prison_management.PrisonAccountant;
    exports com.example.prison_management.mainuser;
    opens com.example.prison_management.mainuser to javafx.fxml;
}