module lk.ijse.serenitymentalhealthsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.controlsfx.controls;
    requires jakarta.persistence;

    opens lk.ijse.serenitymentalhealthsystem to javafx.fxml;
    exports lk.ijse.serenitymentalhealthsystem;
    exports lk.ijse.serenitymentalhealthsystem.controller;
    opens lk.ijse.serenitymentalhealthsystem.controller to javafx.fxml;
}
