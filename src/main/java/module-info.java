module com.example.prison_management {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prison_management to javafx.fxml;
    opens com.example.prison_management.PrisonWarden to javafx.fxml;
    opens com.example.prison_management.Prisoner to javafx.fxml;


    exports com.example.prison_management;
    exports com.example.prison_management.PrisonWarden;
    exports com.example.prison_management.Prisoner;
}