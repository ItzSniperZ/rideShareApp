package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.rideshareapp.Main;

/**
 * Controller class for handling user login functionality in the RideShare application.
 * <p>
 * This class manages the Login screen UI elements and handles login and sign-up button actions.
 * It interacts with the {@link org.example.rideshareapp.services.ProfileService} through
 * {@link org.example.rideshareapp.Main#PROFILE_SERVICE} to verify user credentials.
 * </p>
 *
 * <p><strong>FXML Mapped Elements:</strong></p>
 * <ul>
 *   <li>{@code usernameField} – Text input for the username.</li>
 *   <li>{@code passwordField} – Password input for user authentication.</li>
 *   <li>{@code statusLabel} – Label for displaying login status messages.</li>
 * </ul>
 */
public class LoginController {

    /** Text field for user-entered username. */
    @FXML private TextField usernameField;

    /** Password field for user-entered password. */
    @FXML private PasswordField passwordField;

    /** Label for showing login or error messages. */
    @FXML private Label statusLabel;

    /** Reference to the main application instance for navigation control. */
    private Main mainApp;

    /**
     * Sets the main application reference.
     *
     * @param mainApp the primary {@link Main} application instance
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Triggered when the user presses the Login button.
     * <p>
     * Attempts to authenticate the user via {@link org.example.rideshareapp.services.ProfileService#login(String, String)}.
     * If successful, loads the main application window; otherwise, displays an error message.
     * </p>
     */
    @FXML
    private void onLogin() {
        boolean ok = Main.PROFILE_SERVICE.login(usernameField.getText(), passwordField.getText());
        if (ok) {
            statusLabel.setText("");
            try {
                mainApp.showMainApp();
            } catch (Exception e) {
                e.printStackTrace();
                statusLabel.setText("Unable to load main app.");
            }
        } else {
            statusLabel.setText("Invalid credentials");
        }
    }

    /**
     * Triggered when the user presses the Sign-Up button.
     * <p>
     * Opens the registration window, allowing new users to create accounts.
     * If the window cannot be opened, displays an error message.
     * </p>
     */
    @FXML
    private void onSignup() {
        statusLabel.setText(""); // clear any message
        try {
            mainApp.showSignup(); // navigates to sign-up window
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Unable to open Sign Up.");
        }
    }
}
