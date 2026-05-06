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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button patientManagementBtn;

    @FXML
    private Button therapistManagementBtn;

    @FXML
    private Button programManagementBtn;

    private List<Button> sidebarButtons;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidebarButtons = new ArrayList<>();

        if (dashboardBtn != null) sidebarButtons.add(dashboardBtn);
        if (patientManagementBtn != null) sidebarButtons.add(patientManagementBtn);
        if (therapistManagementBtn != null) sidebarButtons.add(therapistManagementBtn);
        if (programManagementBtn != null) sidebarButtons.add(programManagementBtn);
    }

    //button active
    private void buttonActive(Button activeBtn){
        if (activeBtn == null) {
            System.out.println("Button is NULL!");
            return;
        }

        for (Button btn : sidebarButtons) {
            btn.getStyleClass().remove("sidebar-button-active");
        }

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

    @FXML
    private void loadPatientManagementPage(){
        System.out.println("Loading Patient Management Page...");
        loadUI("/view/PatientManagement.fxml");
        buttonActive(patientManagementBtn);
        System.out.println("BTN :"+patientManagementBtn);

    }

    @FXML
    private void therapistManagementPage(){
        buttonActive(therapistManagementBtn);
        System.out.println("Loading Therapist Management Page...");
        loadUI("/view/TherapistManagement.fxml");
    }

    @FXML
    private void programManagementPage(){
        buttonActive(programManagementBtn);
        System.out.println("Loading Program Management Page...");
        loadUI("/view/ProgramManagement.fxml");
    }

}
