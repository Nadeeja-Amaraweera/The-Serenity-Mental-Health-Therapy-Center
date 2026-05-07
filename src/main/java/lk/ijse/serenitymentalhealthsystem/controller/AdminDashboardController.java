/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.serenitymentalhealthsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author nadeeja
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private StackPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void loadDashboardpage(ActionEvent event) {
        System.out.println("Dashboard button clicked");
        URL url = getClass().getResource("/view/AdminDashboard.fxml");
        System.out.println(url);
        loadUI("/view/AdminDashboard.fxml");
        setActiveButton((Button) event.getSource());
    }

    @FXML
    private void loadPatientManagementPage(ActionEvent event) {
        System.out.println("Patient Management button clicked");
        loadUI("/view/PatientManagement.fxml");
        setActiveButton((Button) event.getSource());
    }

    @FXML
    private void therapistManagementPage(ActionEvent event) {
        loadUI("/view/TherapistManagement.fxml");
        setActiveButton((Button) event.getSource());
    }

    @FXML
    private void programManagementPage(ActionEvent event) {
        loadUI("/view/ProgramManagement.fxml");
        setActiveButton((Button) event.getSource());
    }

    private void setActiveButton(Button activeBtn) {

        Parent parent = activeBtn.getParent();

        for (Node node : parent.getChildrenUnmodifiable()) {

            if (node instanceof Button btn) {

                btn.getStyleClass().remove("sidebar-button-active");

                if (!btn.getStyleClass().contains("sidebar-button")) {
                    btn.getStyleClass().add("sidebar-button");
                }
            }
        }

        activeBtn.getStyleClass().remove("sidebar-button");
        activeBtn.getStyleClass().add("sidebar-button-active");
    }

    //load UIs
    private void loadUI(String path){
        try {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
