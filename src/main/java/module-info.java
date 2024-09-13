module com.example.elsoleclipsado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.elsoleclipsado to javafx.fxml;
    opens com.example.elsoleclipsado.controllers to javafx.fxml;
    exports com.example.elsoleclipsado;
    exports com.example.elsoleclipsado.controllers;
    exports com.example.elsoleclipsado.model.interfaces;
    opens com.example.elsoleclipsado.model.interfaces to javafx.fxml;
    exports com.example.elsoleclipsado.model;
    opens com.example.elsoleclipsado.model to javafx.fxml;
    exports com.example.elsoleclipsado.model.adapters;
    opens com.example.elsoleclipsado.model.adapters to javafx.fxml;
}