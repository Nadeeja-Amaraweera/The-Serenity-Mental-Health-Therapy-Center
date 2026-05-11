/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.serenitymentalhealthsystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.serenitymentalhealthsystem.bo.BOFactory;
import lk.ijse.serenitymentalhealthsystem.bo.BOTypes;
import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;

/**
 * FXML Controller class for New Patient Registration
 *
 * @author nadeeja
 */
public class NewPatientController implements Initializable {

    private final PatientBO patientBO = BOFactory.getInstance().getBO(BOTypes.PATIENT);

    /* Personal Information Fields */
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private DatePicker dpDateOfBirth;
    @FXML
    private ComboBox<String> cmbGender;

    /* Contact Information Fields */
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtState;

    /* Medical Information Fields */
    @FXML
    private ComboBox<String> cmbBloodType;
    @FXML
    private TextField txtAllergies;
    @FXML
    private TextArea taMedicalHistory;

    /* Therapy Information Fields */
    @FXML
    private TextField txtPrimaryConcern;
    @FXML
    private ComboBox<String> cmbTherapyType;
    @FXML
    private ComboBox<String> cmbStatus;
    @FXML
    private TextArea taNotes;

    /* Emergency Contact Fields */
    @FXML
    private TextField txtEmergencyName;
    @FXML
    private TextField txtEmergencyPhone;
    @FXML
    private TextField txtRelationship;

    /* Action Buttons */
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;

    /* Close callback — set by parent controller */
    private Runnable closeCallback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeButtonActions();
    }

    /**
     * Initialize button action handlers
     */
    private void initializeButtonActions() {
        btnSave.setOnAction(event -> handleSavePatient());
        btnCancel.setOnAction(event -> handleCancel());
        btnReset.setOnAction(event -> handleReset());
        btnClose.setOnAction(event -> handleClose());
    }

    /**
     * Handle Save Patient action
     */
    private void handleSavePatient() {
        // Validate form fields
        if (!validateForm()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all required fields");
            return;
        }

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        LocalDate dateOfBirth = dpDateOfBirth.getValue();
        String gender = cmbGender.getValue();
        String phone = txtPhone.getText();
        String status = cmbStatus.getValue();

        PatientDTO patientDTO =  new PatientDTO(
                null,
                firstName,
                lastName,
                dateOfBirth,
                gender,
                phone,
                status
        );

        try {
            boolean result = patientBO.savePatient(patientDTO);
            if (result) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Patient record saved successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // TODO: Implement patient save logic using BO/DAO

        // Clear form after successful save
        clearForm();
    }

    /**
     * Handle Cancel action
     */
    private void handleCancel() {
        // TODO: Navigate back to PatientManagement or close the window
        handleClose(); // For now, just close the overlay
        System.out.println("Cancel pressed - TODO: Navigate back");
    }

    /**
     * Handle Close action — closes the overlay
     * This method closes the NewPatient form overlay
     */
    private void handleClose() {
        clearForm();
        if (closeCallback != null) {
            closeCallback.run();
        }
    }


    public void setCloseCallback(Runnable callback) {
        this.closeCallback = callback;
    }

    /**
     * Handle Reset action
     */
    private void handleReset() {
        clearForm();
    }

    /**
     * Validate form fields
     */
    private boolean validateForm() {
        return !txtFirstName.getText().trim().isEmpty() &&
               !txtLastName.getText().trim().isEmpty() &&
               dpDateOfBirth.getValue() != null &&
               cmbGender.getValue() != null &&
               !txtPhone.getText().trim().isEmpty() &&
               cmbStatus.getValue() != null;
    }

    /**
     * Clear all form fields
     */
    private void clearForm() {
        txtFirstName.clear();
        txtLastName.clear();
        dpDateOfBirth.setValue(null);
        cmbGender.setValue(null);
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtCity.clear();
        txtState.clear();
        cmbBloodType.setValue(null);
        txtAllergies.clear();
        taMedicalHistory.clear();
        txtPrimaryConcern.clear();
        cmbTherapyType.setValue(null);
        cmbStatus.setValue(null);
        taNotes.clear();
        txtEmergencyName.clear();
        txtEmergencyPhone.clear();
        txtRelationship.clear();
    }

    /**
     * Show alert dialog
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
