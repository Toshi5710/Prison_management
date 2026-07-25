module com.example.prison_management {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prison_management to javafx.fxml;
    exports com.example.prison_management;
}