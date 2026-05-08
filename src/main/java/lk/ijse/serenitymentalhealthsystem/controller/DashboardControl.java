package lk.ijse.serenitymentalhealthsystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public interface DashboardControl {
    void loadUIInStackPane(String path , StackPane area);
}
