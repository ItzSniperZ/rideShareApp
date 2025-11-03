package org.example.rideshareapp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
