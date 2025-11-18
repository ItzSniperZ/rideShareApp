package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.rideshareapp.Main;
import org.example.rideshareapp.auth.UserDao;
import org.example.rideshareapp.services.ProfileService;

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

    /** DAO used to check credentials against the database. */
    private final UserDao userDao = new UserDao();

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
     * Attempts to authenticate the user via {@link UserDao#checkCredentials(String, String)}.
     * If successful, loads the main application window; otherwise, displays an error message.
     * </p>
     */
    @FXML

    public String getUsername() {
       return usernameField.getText();
    }
    public String getPassword() {
        return passwordField.getText();
    }
    public String getClassification(String username) {
        return classification.getText();
    }

    private void onLogin() {
        statusLabel.setText(""); // clear old message

        String username = usernameField.getText() == null
                ? ""
                : usernameField.getText().trim();
        String password = passwordField.getText() == null
                ? ""
                : passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter both username and password.");
            return;
        }
        try {
            ProfileService profileService = new ProfileService();
            boolean ok = profileService.login();
            if (ok) {
                // login OK
                statusLabel.setText("");
                System.out.println("[Login] User '" + username + "' logged in successfully.");
                if (mainApp != null) {
                    // Navigate to main app window
                    mainApp.showMainApp();
                } else {
                    // This means setMainApp was never called
                    System.err.println("[Login] mainApp is null. " +
                            "Make sure Main.showLogin() calls controller.setMainApp(this).");
                }
            } else {
                statusLabel.setText("Invalid credentials");
                System.out.println("[Login] Invalid credentials for '" + username + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Login error: " + e.getMessage());
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
            if (mainApp != null) {
                mainApp.showSignup(); // navigates to sign-up window
            } else {
                System.err.println("[Login] mainApp is null in onSignup().");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Unable to open Sign Up.");
        }
    }
}

