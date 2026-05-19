package lk.ijse.serenitymentalhealthsystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lk.ijse.serenitymentalhealthsystem.bo.BOFactory;
import lk.ijse.serenitymentalhealthsystem.bo.BOTypes;
import lk.ijse.serenitymentalhealthsystem.bo.custom.TherapyProgramBO;
import lk.ijse.serenitymentalhealthsystem.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealthsystem.tm.TherapyProgramTM;

/**
 * FXML Controller class for Therapy Program Management
 *
 * @author nadeeja
 */
public class ProgramManagementController implements Initializable {

    private final TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOTypes.THERAPY_PROGRAM);

    @FXML private StackPane mainStackPane;
    @FXML private BorderPane mainContentPane;
    @FXML private StackPane overlayStackPane;


    @FXML private Label lblTotalPrograms;
    @FXML private Label lblActivePrograms;
    @FXML private Label lblPendingPrograms;


    @FXML private TextField txtProgramName;
    @FXML private ComboBox<String> cmbTherapyType;
    @FXML private TextField txtDuration;
    @FXML private ComboBox<String> cmbFrequency;
    @FXML private DatePicker dpStartDate;
    @FXML private DatePicker dpEndDate;
    @FXML private ComboBox<String> cmbStatus;
    @FXML private TextArea txtDescription;


    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private Button btnClear;
    @FXML private Button btnDelete;


    @FXML private TextField txtSearchProgram;
    @FXML private ComboBox<String> cmbFilterStatus;
    @FXML private ComboBox<String> cmbFilterType;
    @FXML private Button btnExport;


    @FXML private TableView<TherapyProgramTM> tblPrograms;
    @FXML private TableColumn<TherapyProgramTM, Integer> colProgramId;
    @FXML private TableColumn<TherapyProgramTM, String> colProgramName;
    @FXML private TableColumn<TherapyProgramTM, String> colTherapyType;
    @FXML private TableColumn<TherapyProgramTM, Integer> colDuration;
    @FXML private TableColumn<TherapyProgramTM, String> colFrequency;
    @FXML private TableColumn<TherapyProgramTM, LocalDate> colStartDate;
    @FXML private TableColumn<TherapyProgramTM, LocalDate> colEndDate;
    @FXML private TableColumn<TherapyProgramTM, String> colStatus;


    @FXML private Button btnFirstPage;
    @FXML private Button btnPrevPage;
    @FXML private Label lblCurrentPage;
    @FXML private Label lblTotalPages;
    @FXML private Button btnNextPage;
    @FXML private Button btnLastPage;
    @FXML private ComboBox<String> cmbRowsPerPage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeUI();
        loadProgramData();

        tblPrograms.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        btnUpdate.setDisable(false);
                        btnDelete.setDisable(false);
                        btnSave.setDisable(true);

                        txtProgramName.setText(newValue.getProgramName());
                        cmbTherapyType.setValue(newValue.getTherapyType());
                        txtDuration.setText(String.valueOf(newValue.getDuration()));
                        cmbFrequency.setValue(newValue.getFrequency());

                        dpStartDate.setValue(newValue.getStartDate());
                        dpEndDate.setValue(newValue.getEndDate());

                        cmbStatus.setValue(newValue.getStatus());

                        txtDescription.setText(newValue.getDescription());

                    }
                }

        );
    }


    private void initializeUI() {
        // Set default statistics
        lblTotalPrograms.setText("0");
        lblActivePrograms.setText("0");
        lblPendingPrograms.setText("0");

        // Initialize ComboBoxes if not already defined in FXML
        if (cmbRowsPerPage.getItems().isEmpty()) {
            cmbRowsPerPage.setItems(FXCollections.observableArrayList(
                    "10 rows", "20 rows", "50 rows", "100 rows"
            ));
            cmbRowsPerPage.setValue("20 rows");
        }

        // Set pagination to page 1
        lblCurrentPage.setText("1");
        lblTotalPages.setText("1");

        // Configure table columns with proper cell factories
        configureTableColumns();

        // Add validators and listeners
        setupFieldValidators();
    }


    private void configureTableColumns() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colTherapyType.setCellValueFactory(new PropertyValueFactory<>("therapyType"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFrequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    private void setupFieldValidators() {
        // Duration field - only accept numbers
        txtDuration.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                txtDuration.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        // Search field listener for real-time search
        txtSearchProgram.textProperty().addListener((obs, oldVal, newVal) -> {
            performSearch(newVal);
        });

        // Filter listeners
        cmbFilterStatus.setOnAction(e -> applyFilters());
        cmbFilterType.setOnAction(e -> applyFilters());
    }

    private void loadProgramData() {
        try {

        List<TherapyProgramDTO> list = therapyProgramBO.getAllPrograms();
        ObservableList<TherapyProgramTM> observableList = FXCollections.observableArrayList();

        for (TherapyProgramDTO dto : list) {
            observableList.add(new TherapyProgramTM(
                    dto.getProgramId(),
                    dto.getProgramName(),
                    dto.getTherapyType(),
                    dto.getDuration(),
                    dto.getFrequency(),
                    dto.getStartDate(),
                    dto.getEndDate(),
                    dto.getDescription(),
                    dto.getStatus()
            ));
        }
        tblPrograms.setItems(observableList);
        } catch (Exception e){
            showError("Data Load Error", "Failed to load therapy programs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void SaveProgram(ActionEvent event) {
        if (!validateFormFields()) {
            showError("Validation Error", "Please fill all required fields");
            return;
        }

        try {
            // Create program object with form data
            String programId = therapyProgramBO.getNextId();
            String programName = txtProgramName.getText();
            String therapyType = cmbTherapyType.getValue();
            int duration = Integer.parseInt(txtDuration.getText());
            String frequency = cmbFrequency.getValue();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();
            String status = cmbStatus.getValue();
            String description = txtDescription.getText();

            // Validate dates
            if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
                showError("Date Error", "End date must be after start date");
                return;
            }

            TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                    programId,
                    programName,
                    therapyType,
                    duration,
                    frequency,
                    startDate,
                    endDate,
                    status,
                    description
            );

            System.out.println("Saving program: " + therapyProgramDTO);

            boolean result = therapyProgramBO.saveProgram(therapyProgramDTO);

            if (!result) {
                showError("Save Error", "Failed to save therapy program");
                return;
            }

            showSuccess("Program Saved", "Therapy program has been saved successfully");
            ClearForm(null);
            loadProgramData();
        } catch (NumberFormatException e) {
            showError("Input Error", "Duration must be a valid number");
        } catch (Exception e) {
            showError("Error", "Failed to save program: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void UpdateProgram(ActionEvent event) {
        if (tblPrograms.getSelectionModel().isEmpty()) {
            showError("Selection Error", "Please select a program to update");
            return;
        }

        if (!validateFormFields()) {
            showError("Validation Error", "Please fill all required fields");
            return;
        }

        try {
            String programId = tblPrograms.getSelectionModel().getSelectedItem().getProgramId();
            String programName = txtProgramName.getText();
            String therapyType = cmbTherapyType.getValue();
            int duration = Integer.parseInt(txtDuration.getText());
            String frequency = cmbFrequency.getValue();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();
            String status = cmbStatus.getValue();
            String description = txtDescription.getText();

            TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                    programId,
                    programName,
                    therapyType,
                    duration,
                    frequency,
                    startDate,
                    endDate,
                    status,
                    description
            );

            boolean result = therapyProgramBO.updateProgram(therapyProgramDTO);

            if (!result) {
                showError("Update Error", "Failed to update therapy program");
                return;
            }

            showSuccess("Program Updated", "Therapy program has been updated successfully");
            ClearForm(null);
            loadProgramData();
        } catch (Exception e) {
            showError("Error", "Failed to update program: " + e.getMessage());
        }
    }

    @FXML
    private void ClearForm(ActionEvent event) {
        txtProgramName.clear();
        cmbTherapyType.setValue(null);
        txtDuration.clear();
        cmbFrequency.setValue(null);
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        cmbStatus.setValue(null);
        txtDescription.clear();

        // Deselect table selection
        tblPrograms.getSelectionModel().clearSelection();

        // Reset button states
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
    }

    @FXML
    private void DeleteProgram(ActionEvent event) {
        if (tblPrograms.getSelectionModel().isEmpty()) {
            showError("Selection Error", "Please select a program to delete");
            return;
        }

        try {
            String programId = tblPrograms.getSelectionModel().getSelectedItem().getProgramId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Do you really want to proceed?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Deletion cancelled by user");
                boolean rs = therapyProgramBO.deleteProgram(programId);
                if (rs) {
                    loadProgramData();
                    showSuccess("Program Deleted", "Therapy program has been deleted successfully");
                    ClearForm(null);
                }
            }



            loadProgramData();
        } catch (Exception e) {
            showError("Error", "Failed to delete program: " + e.getMessage());
        }
    }


    private void performSearch(String searchTerm) {
        // TODO: Implement search functionality
        // Filter table based on program name, type, or therapist
        if (searchTerm.isEmpty()) {
            loadProgramData();
        } else {
            // Perform filtered search
        }
    }

    private void applyFilters() {
        String statusFilter = cmbFilterStatus.getValue();
        String typeFilter = cmbFilterType.getValue();

        // TODO: Apply filters to table data
        loadProgramData();
    }

    @FXML
    private void handleExport(ActionEvent event) {
        try {
            // TODO: Implement export to CSV/Excel
            showSuccess("Export", "Programs exported successfully");
        } catch (Exception e) {
            showError("Export Error", "Failed to export programs: " + e.getMessage());
        }
    }


    @FXML
    private void handleFirstPage(ActionEvent event) {
        // TODO: Navigate to first page
        lblCurrentPage.setText("1");
    }

    @FXML
    private void handlePrevPage(ActionEvent event) {
        // TODO: Navigate to previous page
        int currentPage = Integer.parseInt(lblCurrentPage.getText());
        if (currentPage > 1) {
            lblCurrentPage.setText(String.valueOf(currentPage - 1));
        }
    }

    @FXML
    private void handleNextPage(ActionEvent event) {
        // TODO: Navigate to next page
        int currentPage = Integer.parseInt(lblCurrentPage.getText());
        int totalPages = Integer.parseInt(lblTotalPages.getText());
        if (currentPage < totalPages) {
            lblCurrentPage.setText(String.valueOf(currentPage + 1));
        }
    }

    @FXML
    private void handleLastPage(ActionEvent event) {
        // TODO: Navigate to last page
        lblCurrentPage.setText(lblTotalPages.getText());
    }

    @FXML
    private void handleRowsPerPageChange(ActionEvent event) {
        // TODO: Update table rows per page
        loadProgramData();
    }

    private boolean validateFormFields() {
        return !txtProgramName.getText().trim().isEmpty() &&
                cmbTherapyType.getValue() != null &&
                !txtDuration.getText().trim().isEmpty() &&
                cmbFrequency.getValue() != null &&
                dpStartDate.getValue() != null &&
                dpEndDate.getValue() != null &&
                cmbStatus.getValue() != null;
    }


    private void showError(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showSuccess(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
