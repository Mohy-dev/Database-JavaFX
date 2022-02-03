module com.example.javafxnotepad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.logging;
    requires java.sql;
    requires mysql.connector;

    exports dataBase;
}