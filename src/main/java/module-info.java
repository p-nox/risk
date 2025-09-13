module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.model;
    opens com.example.model to javafx.fxml;
    exports com.example.controllers;
    opens com.example.controllers to javafx.fxml;
}