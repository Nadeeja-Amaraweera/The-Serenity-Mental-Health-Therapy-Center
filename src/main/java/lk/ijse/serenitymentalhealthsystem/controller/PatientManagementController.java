/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.serenitymentalhealthsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import lk.ijse.serenitymentalhealthsystem.bo.BOFactory;
import lk.ijse.serenitymentalhealthsystem.bo.BOTypes;
import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;
import lk.ijse.serenitymentalhealthsystem.tm.PatientTM;

/**
 * FXML Controller class for Patient Management
 * Manages patient list, detail panel, and dynamic view loading via StackPane
 *
 * @author nadeeja
 */
public class PatientManagementController implements Initializable, DashboardControl {

    /**
     * Main StackPane container that holds the primary patient management view
     */
    @FXML
    private StackPane mainStackPane;

    /**
     * Overlay StackPane for loading additional views on top of the main view
     * Can be used to load dialogs, forms, or other overlays
     */
    @FXML
    private StackPane overlayStackPane;

    /**
     * BorderPane within the StackPane for layout management
     */
    @FXML
    private BorderPane mainContentPane;

    /**
     * Initializes the controller class.
     */

    @FXML
    private TableView<PatientTM> tblPatients;

    @FXML
    private TableColumn<PatientTM, Long> colId;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, Integer> colAge;

    @FXML
    private TableColumn<PatientTM, String> colGender;

    @FXML
    private TableColumn<PatientTM, String> colContact;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

//    @FXML
//    private TableColumn<PatientTM, Integer> colPrograms;
//
//    @FXML
//    private TableColumn<PatientTM, String> colRegistered;

    @FXML
    private TableColumn<PatientTM, String> colStatus;

    @FXML
    private TableColumn<PatientTM, Button> colActions;

    private final PatientBO patientBO = (BOFactory.getInstance().getBO(BOTypes.PATIENT));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        colPrograms.setCellValueFactory(new PropertyValueFactory<>("programCount"));
//        colRegistered.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("actions"));

        loadPatients();

        // Initialize overlay as hidden
        if (overlayStackPane != null) {
            overlayStackPane.setVisible(false);
            overlayStackPane.setManaged(false);
        }
    }

    private void loadPatients() {
        try {
            List<PatientDTO> list = patientBO.getAllPatients();
            ObservableList<PatientTM> observableList = FXCollections.observableArrayList();
            for (PatientDTO patientDTO : list) {
                Button btnView = new Button("View");
                btnView.setOnAction(event -> {
                    // Handle view patient details
                    System.out.println("View details for patient ID: " + patientDTO.getPatientId());
                    // You can load a detailed view in the overlay here
                    loadViewInOverlay("/view/PatientDetails.fxml");
                });

                int age = Period.between(patientDTO.getDateOfBirth(), java.time.LocalDate.now()).getYears();

                observableList.add(new PatientTM(
                        patientDTO.getPatientId(),
                        patientDTO.getFirstName()+ " " +patientDTO.getLastName(),
                        age,
                        patientDTO.getGender(),
                        patientDTO.getPhone(),
                        patientDTO.getEmail(),
                        patientDTO.getStatus(),
                        btnView

                ));
            }

            tblPatients.setItems(observableList);

        } catch (Exception e) {
            System.err.println("Error loading patients: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle Add New Patient button click
     * Loads NewPatient form into the overlay StackPane
     */
    @FXML
    private void AddNewPatient(ActionEvent event) {
        System.out.println("Add New Patient button clicked");
        loadViewInOverlay("/view/NewPatient.fxml");
    }

    /**
     * Load a view into the main content area (BorderPane)
     * Legacy method for compatibility with DashboardControl interface
     */


    /**
     * Load a view into the overlay StackPane
     * Used for modal-like overlays, dialogs, and forms that appear on top
     *
     * @param fxmlPath Path to the FXML file (e.g., "/view/NewPatient.fxml")
     */
    public void loadViewInOverlay(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            overlayStackPane.getChildren().clear();
            overlayStackPane.getChildren().add(root);
            overlayStackPane.setVisible(true);
            overlayStackPane.setManaged(true);

            // If the loaded controller has a close button, set up close handler
            if (loader.getController() instanceof NewPatientController) {
                NewPatientController controller = loader.getController();
                controller.setCloseCallback(this::hideOverlay);
            }
        } catch (IOException e) {
            System.err.println("Error loading overlay view: " + fxmlPath);
            e.printStackTrace();
        }
    }

    /**
     * Hide the overlay StackPane
     */
    public void hideOverlay() {
        if (overlayStackPane != null) {
            overlayStackPane.getChildren().clear();
            overlayStackPane.setVisible(false);
            overlayStackPane.setManaged(false);
        }
    }

    /**
     * Load a view into the main content area (StackPane)
     *
     * @param fxmlPath Path to the FXML file
     */
    public void loadViewInMain(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainStackPane.getChildren().clear();
            mainStackPane.getChildren().add(root);
        } catch (IOException e) {
            System.err.println("Error loading main view: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @Override
    public void loadUIInStackPane(String path, StackPane area) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            area.getChildren().clear();
            area.getChildren().add(root);
        } catch (IOException e) {
            System.err.println("Error loading view: " + path);
            e.printStackTrace();
        }
    }
}


