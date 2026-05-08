/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.serenitymentalhealthsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class for Therapist Management
 * Manages therapist profiles, specializations, availability and program assignments
 *
 * @author nadeeja
 */
public class TherapistManagementController implements Initializable, DashboardControl {

    /**
     * Main StackPane container that holds the primary therapist management view
     */
    @FXML
    private StackPane mainStackPane;

    /**
     * Overlay StackPane for loading additional views on top of the main view
     */
    @FXML
    private StackPane overlayStackPane;

    /**
     * BorderPane within the StackPane for layout management
     */
    @FXML
    private BorderPane mainContentPane;

    /* Header Stat Labels */
    @FXML
    private Label lblTotalTherapists;
    @FXML
    private Label lblAvailableTherapists;
    @FXML
    private Label lblBusyTherapists;

    /* Search and Filter Controls */
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> cmbSpecializationFilter;
    @FXML
    private ComboBox<String> cmbAvailabilityFilter;

    /* Main Table */
    @FXML
    private TableView<?> tblTherapists;

    /* Pagination Controls */
    @FXML
    private Button btnFirstPage;
    @FXML
    private Button btnPrevPage;
    @FXML
    private Label lblCurrentPage;
    @FXML
    private Label lblTotalPages;
    @FXML
    private Button btnNextPage;
    @FXML
    private Button btnLastPage;
    @FXML
    private ComboBox<String> cmbRowsPerPage;

    /* Action Buttons */
    @FXML
    private Button btnAddTherapist;
    @FXML
    private Button btnExport;

    /* Detail Panel */
    @FXML
    private VBox detailPanel;
    @FXML
    private Label lblDetailName;
    @FXML
    private Label lblDetailId;
    @FXML
    private Label lblDetailStatus;
    @FXML
    private Label lblDetailInitials;
    @FXML
    private Label lblDetailEmail;
    @FXML
    private Label lblDetailPhone;
    @FXML
    private Label lblDetailSpecialization;
    @FXML
    private Label lblDetailAvailability;
    @FXML
    private Label lblDetailProgram;
    @FXML
    private ScrollPane detailScroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize overlay as hidden
        if (overlayStackPane != null) {
            overlayStackPane.setVisible(false);
            overlayStackPane.setManaged(false);
        }
        // Initialize UI components
        initializeListeners();
    }

    /**
     * Initialize UI event listeners
     */
    private void initializeListeners() {
        if (btnAddTherapist != null) {
            btnAddTherapist.setOnAction(this::handleAddTherapist);
        }
    }

    /**
     * Handle Add Therapist button click
     */
    @FXML
    private void handleAddTherapist(ActionEvent event) {
        System.out.println("Add New Therapist button clicked");
        loadViewInOverlay("/view/NewTherapist.fxml");
    }

    /**
     * Add New Therapist menu action
     */
    @FXML
    private void AddNewTherapist(ActionEvent event) {
        System.out.println("Add New Therapist clicked");
        handleAddTherapist(event);
    }

    /**
     * Load a view into the overlay StackPane
     * Used for modal-like overlays, dialogs, and forms that appear on top
     *
     * @param fxmlPath Path to the FXML file (e.g., "/view/NewTherapist.fxml")
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

    /**
     * Load UI into a StackPane (DashboardControl interface implementation)
     */
    @Override
    public void loadUIInStackPane(String path, StackPane area) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            area.getChildren().clear();
            area.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
