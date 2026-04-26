package lk.ijse.serenitymentalhealthsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealthsystem.App;


public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private CheckBox showPasswordCheckbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sync text
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        // Fix layout (VERY IMPORTANT)
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        Platform.runLater(() -> usernameField.requestFocus());

    }

    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = showPasswordCheckbox.isSelected() ? visiblePasswordField.getText() : passwordField.getText();

        // Check empty fields FIRST
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter username and password.");
            return;
        }

        // Then check credentials
        if (username.equals("admin") && password.equals("123")) {

            System.out.println("admin Login successful!");
            // TODO: Navigate to admin dashboard
            Parent layoutFXML = App.loadFXML("AdminDashboard");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(layoutFXML);
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();


        } else if (username.equals("user") && password.equals("123")) {

            System.out.println("user Login successful!");
            // TODO: Navigate to user dashboard
            Parent layoutFXML = App.loadFXML("ReceptionistDashboard");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(layoutFXML);
            stage.setScene(scene);
            stage.setTitle("Receptionist Dashboard");
            stage.show();

        } else {
            System.out.println("Invalid username or password.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login not successful!");
            alert.show();

        }
    }

    @FXML
    private void togglePasswordVisibility() {
        if (showPasswordCheckbox.isSelected()) {
            visiblePasswordField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            visiblePasswordField.setVisible(false);
            passwordField.setVisible(true);
        }
    }




}
