package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.rideshareapp.auth.UserDao;

public class SignupController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    @FXML
    private void onRegister() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText();
        if (u.isEmpty() || p.isEmpty()) { statusLabel.setText("Enter username & password"); return; }

        boolean created;
        try {
            created = new UserDao().createUser(u, p);   // writes to SQLite (BCrypt hash)
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error creating user");
            return;
        }

        if (created) {
            // Go back to log in screen
            try {
                var fx = new FXMLLoader(getClass().getResource("/org/example/rideshareapp/login.fxml"));
                Stage st = (Stage) statusLabel.getScene().getWindow();
                st.setScene(new Scene(fx.load()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Username already taken");
        }
    }

    @FXML
    private void onBackToLogin() {
        try {
            var fx = new FXMLLoader(getClass().getResource("/org/example/rideshareapp/login.fxml"));
            Stage st = (Stage) statusLabel.getScene().getWindow();
            st.setScene(new Scene(fx.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

