package lk.ijse.serenitymentalhealthsystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class for Therapy Program Management
 *
 * @author nadeeja
 */
public class ProgramManagementController implements Initializable {


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


    @FXML private TableView<Object> tblPrograms;
    @FXML private TableColumn<Object, Integer> colProgramId;
    @FXML private TableColumn<Object, String> colProgramName;
    @FXML private TableColumn<Object, String> colTherapyType;
    @FXML private TableColumn<Object, Integer> colDuration;
    @FXML private TableColumn<Object, String> colFrequency;
    @FXML private TableColumn<Object, LocalDate> colStartDate;
    @FXML private TableColumn<Object, LocalDate> colEndDate;
    @FXML private TableColumn<Object, String> colStatus;
    @FXML private TableColumn<Object, String> colActions;


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
        // Column configuration will depend on the data model
        // This is a placeholder for actual implementation
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

    /**
     * Load program data into the table
     */
    private void loadProgramData() {
        // TODO: Implement data loading from DAO
        // This will fetch programs from database and populate the table
    }

    @FXML
    private void SaveProgram(ActionEvent event) {
        if (!validateFormFields()) {
            showError("Validation Error", "Please fill all required fields");
            return;
        }

        try {
            // Create program object with form data
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

            // TODO: Call DAO to save program
            // programBO.saveProgram(new ProgramDTO(...));

            showSuccess("Program Saved", "Therapy program has been saved successfully");
            ClearForm(null);
            loadProgramData();
        } catch (NumberFormatException e) {
            showError("Input Error", "Duration must be a valid number");
        } catch (Exception e) {
            showError("Error", "Failed to save program: " + e.getMessage());
        }
    }

    /**
     * Handle Update Program button action
     */
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
            // TODO: Implement update logic
            // Get selected program and update its data

            showSuccess("Program Updated", "Therapy program has been updated successfully");
            ClearForm(null);
            loadProgramData();
        } catch (Exception e) {
            showError("Error", "Failed to update program: " + e.getMessage());
        }
    }

    /**
     * Handle Clear Form button action
     */
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
    }

    /**
     * Handle Delete Program button action
     */
    @FXML
    private void DeleteProgram(ActionEvent event) {
        if (tblPrograms.getSelectionModel().isEmpty()) {
            showError("Selection Error", "Please select a program to delete");
            return;
        }

        try {
            // TODO: Show confirmation dialog
            // if (confirmDelete()) {
            //     call DAO to delete
            //     loadProgramData();
            //     ClearForm(null);
            // }

            showSuccess("Program Deleted", "Therapy program has been deleted successfully");
        } catch (Exception e) {
            showError("Error", "Failed to delete program: " + e.getMessage());
        }
    }

    // ╔════════════════════════════════════════════════════╗
    // ║ TABLE & SEARCH/FILTER HANDLERS                     ║
    // ╚════════════════════════════════════════════════════╝

    /**
     * Perform search on programs
     */
    private void performSearch(String searchTerm) {
        // TODO: Implement search functionality
        // Filter table based on program name, type, or therapist
        if (searchTerm.isEmpty()) {
            loadProgramData();
        } else {
            // Perform filtered search
        }
    }

    /**
     * Apply filters to program list
     */
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

    // ╔════════════════════════════════════════════════════╗
    // ║ VALIDATION & HELPER METHODS                        ║
    // ╚════════════════════════════════════════════════════╝

    /**
     * Validate all required form fields
     */
    private boolean validateFormFields() {
        return !txtProgramName.getText().trim().isEmpty() &&
                cmbTherapyType.getValue() != null &&
                !txtDuration.getText().trim().isEmpty() &&
                cmbFrequency.getValue() != null &&
                dpStartDate.getValue() != null &&
                dpEndDate.getValue() != null &&
                cmbStatus.getValue() != null;
    }

    /**
     * Show error message dialog
     */
    private void showError(String title, String message) {
        System.err.println(title + ": " + message);
        // TODO: Implement proper error dialog using javafx.scene.control.Alert
    }

    /**
     * Show success message dialog
     */
    private void showSuccess(String title, String message) {
        System.out.println(title + ": " + message);
        // TODO: Implement proper success dialog using javafx.scene.control.Alert
    }
}
