package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import org.example.rideshareapp.auth.UserDao;
import  org.example.rideshareapp.services.ProfileService;

/**
 * Controller class for handling user registration in the RideShare application.
 * <p>
 * This class manages the sign-up process, including username/password validation,
 * creating a new user in the database via {@link UserDao}, and navigating between
 * the Sign-Up and Login screens.
 * </p>
 *
 * <p><strong>FXML Mapped Elements:</strong></p>
 * <ul>
 *     <li>{@code usernameField} — Text input for the new user’s username.</li>
 *     <li>{@code passwordField} — Password input for the new user’s password.</li>
 *     <li>{@code statusLabel} — Label for displaying registration feedback or errors.</li>
 * </ul>
 *
 * <p><strong>Features:</strong></p>
 * <ul>
 *     <li>Validates user input before submission.</li>
 *     <li>Creates a new user in the database using bcrypt password hashing.</li>
 *     <li>Automatically returns to the Login screen after successful registration.</li>
 *     <li>Handles navigation back to the Login screen if registration is canceled.</li>
 * </ul>
 */
public class SignupController {

    /** Text field for entering the new user's username. */
    @FXML private TextField usernameField;

    /** Password field for entering the new user's password. */
    @FXML private PasswordField passwordField;

    /** Label displaying registration or validation status messages. */
    @FXML private Label statusLabel;

    @FXML private TextField classField;

    /**
     * Triggered when the user clicks the “Register” button.
     * <p>
     * Validates input fields and attempts to create a new user in the database.
     * If registration succeeds, the controller navigates back to the Login screen.
     * If the username already exists or an error occurs, a message is displayed.
     * </p>
     */
    @FXML
    private void onRegister() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText();
        String c = classField.getText().trim();

        if (u.isEmpty() || p.isEmpty() || c.isEmpty()) {
            statusLabel.setText("Enter username, password, and classification");
            return;
        }

        ProfileService profileService = new ProfileService();
        boolean created;

        try {
            created = profileService.register(u, p, c);
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error creating user");
            return;
        }

        if (created) {
            // Return user to login screen
            try {
                FXMLLoader fx = new FXMLLoader(getClass().getResource(
                        "/org/example/rideshareapp/login.fxml"));
                Stage st = (Stage) statusLabel.getScene().getWindow();
                st.setScene(new Scene(fx.load()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Username already taken or error occurred");
        }
    }

    /**
     * Triggered when the user clicks the “Back to Login” button.
     * <p>
     * Loads and displays the Login screen by replacing the current scene.
     * </p>
     */
    @FXML
    private void onBackToLogin() {
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource("/org/example/rideshareapp/login.fxml"));
            Stage st = (Stage) statusLabel.getScene().getWindow();
            st.setScene(new Scene(fx.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
