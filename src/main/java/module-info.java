module lk.ijse.serenitymentalhealthsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires jakarta.persistence;
    requires static lombok;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens lk.ijse.serenitymentalhealthsystem to javafx.fxml;
    exports lk.ijse.serenitymentalhealthsystem;
    exports lk.ijse.serenitymentalhealthsystem.controller;
    opens lk.ijse.serenitymentalhealthsystem.controller to javafx.fxml;
}
