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

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

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

    // Open the Sign-Up window
    @FXML
    private void onSignup() {
        statusLabel.setText(""); // clear any message
        try {
            mainApp.showSignup();     // just like mainApp.showMainApp();
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Unable to open Sign Up.");
        }
    }

}

