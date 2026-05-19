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

    private final PatientManagementController patientManagementController = new PatientManagementController();

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

    private Long currentPatientId;



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
        btnSave.setOnAction(event -> {
            if (btnSave.getText().equals("Save Patient")) {
                handleSavePatient();
            } else {
                handleUpdatePatient();
            }
        });
        btnCancel.setOnAction(event -> handleCancel());
        btnReset.setOnAction(event -> handleReset());
        btnClose.setOnAction(event -> handleClose());
    }

    private void handleUpdatePatient() {
        System.out.println("Update button pressed");
        if (!validateForm()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all required fields");
            return;
        }
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        LocalDate dateOfBirth = dpDateOfBirth.getValue();
        String gender = cmbGender.getValue();

        /* Contact Information */
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String state = txtState.getText();

        /* Medical Information */
        String bloodType = cmbBloodType.getValue();
        String allergies = txtAllergies.getText();
        String medicalHistory = taMedicalHistory.getText();

        /* Therapy Information */
        String primaryConcern = txtPrimaryConcern.getText();
        String therapyType = cmbTherapyType.getValue();
        String status = cmbStatus.getValue();
        String notes = taNotes.getText();

        /* Emergency Contact */
        String emergencyName = txtEmergencyName.getText();
        String emergencyPhone = txtEmergencyPhone.getText();
        String relationship = txtRelationship.getText();

        PatientDTO patientDTO = new PatientDTO(
                currentPatientId,
                firstName,
                lastName,
                dateOfBirth,
                gender,

                // Contact Information
                email,
                phone,
                address,
                city,
                state,

                // Medical Information
                bloodType,
                allergies,
                medicalHistory,

                // Therapy Information
                primaryConcern,
                therapyType,
                status,
                notes,

                // Emergency Contact
                emergencyName,
                emergencyPhone,
                relationship
        );

        try {
            System.out.println(patientDTO);
           boolean result = patientBO.updatePatient(patientDTO);
            if (result) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Patient record updated successfully");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         clearForm();

    }

    private void handleSavePatient() {

        System.out.println("Save button pressed");

        if (!validateForm()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all required fields");
            return;
        }

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        LocalDate dateOfBirth = dpDateOfBirth.getValue();
        String gender = cmbGender.getValue();

        /* Contact Information */
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String state = txtState.getText();

        /* Medical Information */
        String bloodType = cmbBloodType.getValue();
        String allergies = txtAllergies.getText();
        String medicalHistory = taMedicalHistory.getText();

        /* Therapy Information */
        String primaryConcern = txtPrimaryConcern.getText();
        String therapyType = cmbTherapyType.getValue();
        String status = cmbStatus.getValue();
        String notes = taNotes.getText();

        /* Emergency Contact */
        String emergencyName = txtEmergencyName.getText();
        String emergencyPhone = txtEmergencyPhone.getText();
        String relationship = txtRelationship.getText();

        PatientDTO patientDTO = new PatientDTO(
                null,
                firstName,
                lastName,
                dateOfBirth,
                gender,

                // Contact Information
                email,
                phone,
                address,
                city,
                state,

                // Medical Information
                bloodType,
                allergies,
                medicalHistory,

                // Therapy Information
                primaryConcern,
                therapyType,
                status,
                notes,

                // Emergency Contact
                emergencyName,
                emergencyPhone,
                relationship
        );

        try {
            boolean result = patientBO.savePatient(patientDTO);
            if (result) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Patient record saved successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        clearForm();
    }

    public void viewPatientDetails(PatientDTO dto){

        currentPatientId = dto.getPatientId();

        txtFirstName.setText(dto.getFirstName());
        txtLastName.setText(dto.getLastName());

        dpDateOfBirth.setValue(dto.getDateOfBirth());

        cmbGender.setValue(dto.getGender());

        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());

        txtAddress.setText(dto.getAddress());
        txtCity.setText(dto.getCity());
        txtState.setText(dto.getState());

        cmbBloodType.setValue(dto.getBloodType());

        txtAllergies.setText(dto.getAllergies());

        taMedicalHistory.setText(dto.getMedicalHistory());

        txtPrimaryConcern.setText(dto.getPrimaryConcern());

        cmbTherapyType.setValue(dto.getTherapyType());

        cmbStatus.setValue(dto.getStatus());

        taNotes.setText(dto.getNotes());

        txtEmergencyName.setText(dto.getEmergencyName());

        txtEmergencyPhone.setText(dto.getEmergencyPhone());

        txtRelationship.setText(dto.getRelationship());

    }

    private void handleCancel() {
        // TODO: Navigate back to PatientManagement or close the window
        handleClose(); // For now, just close the overlay
        System.out.println("Cancel pressed - TODO: Navigate back");
    }

    private void handleClose() {

        clearForm();
        if (closeCallback != null) {
            closeCallback.run();
        }
    }

    public void setCloseCallback(Runnable callback) {
        this.closeCallback = callback;
    }

    private void handleReset() {
        clearForm();
    }

    private boolean validateForm() {
        return !txtFirstName.getText().trim().isEmpty() &&
               !txtLastName.getText().trim().isEmpty() &&
               dpDateOfBirth.getValue() != null &&
               cmbGender.getValue() != null &&
               !txtPhone.getText().trim().isEmpty() &&
               cmbStatus.getValue() != null;
    }

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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void enableUpdateMode(){
        btnSave.setText("Update");
    }
}
