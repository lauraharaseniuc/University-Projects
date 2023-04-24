module com.example.lab10_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab10_gui to javafx.fxml;
    exports com.example.lab10_gui;

    opens com.example.lab10_gui.entities to javafx.fxml, javafx.base;
    exports com.example.lab10_gui.entities;

    exports com.example.lab10_gui.business;
    opens com.example.lab10_gui.business to javafx.fxml;
}