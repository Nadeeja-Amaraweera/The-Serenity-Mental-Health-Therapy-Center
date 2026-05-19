/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.serenitymentalhealthsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lk.ijse.serenitymentalhealthsystem.bo.BOFactory;
import lk.ijse.serenitymentalhealthsystem.bo.BOTypes;
import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;
import lk.ijse.serenitymentalhealthsystem.tm.PatientTM;

/**
 * @author nadeeja
 */
public class PatientManagementController implements Initializable, DashboardControl {

    @FXML
    private StackPane mainStackPane;

    @FXML
    private StackPane overlayStackPane;

    @FXML
    private BorderPane mainContentPane;

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

    public void loadPatients() {
        try {
            List<PatientDTO> list = patientBO.getAllPatients();
            ObservableList<PatientTM> observableList = FXCollections.observableArrayList();

            for (PatientDTO patientDTO : list) {
                Button btnView = new Button("View");
                Button btnDelete = new Button("Delete");

                HBox buttons = new HBox(2); // spacing 10

                buttons.getChildren().addAll(btnView, btnDelete);

                btnView.setOnAction(event -> {
                    System.out.println("View details for patient ID: "
                            + patientDTO.getPatientId());

                    NewPatientController controller =
                            loadViewInOverlay("/view/NewPatient.fxml");

                    if (controller != null) {
                        controller.viewPatientDetails(patientDTO);
                        controller.enableUpdateMode();

                    }
                });

                btnDelete.setOnAction(event -> {

                            Long id =  patientDTO.getPatientId();
                    try {
                        deletePatient(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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
                        buttons

                ));
            }

            tblPatients.setItems(observableList);

        } catch (Exception e) {
            System.err.println("Error loading patients: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deletePatient(Long patientId) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you really want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean rs = patientBO.deletePatient(String.valueOf(patientId));
            if (rs) {
                loadPatients();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success");
                alert1.setHeaderText(null);
                alert1.setContentText("Patient deleted successfully!");
                alert1.showAndWait();
            }
        }
    }

    @FXML
    private void AddNewPatient(ActionEvent event) {
        System.out.println("Add New Patient button clicked");
        loadViewInOverlay("/view/NewPatient.fxml");
    }

    public NewPatientController loadViewInOverlay(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            overlayStackPane.getChildren().clear();
            overlayStackPane.getChildren().add(root);
            overlayStackPane.setVisible(true);
            overlayStackPane.setManaged(true);

            NewPatientController controller =
                    loader.getController();

            controller.setCloseCallback(this::hideOverlay);

            return controller;

        } catch (IOException e) {
            System.err.println("Error loading overlay view: " + fxmlPath);
            e.printStackTrace();
        }
        return null;
    }

    public void hideOverlay() {
        if (overlayStackPane != null) {
            overlayStackPane.getChildren().clear();
            overlayStackPane.setVisible(false);
            overlayStackPane.setManaged(false);
        }
    }

    public void loadViewInMain(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath)
            );

            Parent root = loader.load();

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


