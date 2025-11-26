package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import org.example.rideshareapp.auth.UserDao;

import java.io.IOException;

public class DriverController {
    @FXML private Label label1;

    @FXML private Button profileButton;

    @FXML private Button rideRequestButton;

    @FXML private Button logOutButton;

    @FXML // Method runs when profile button is clicked
    private void onProfileButton() throws IOException {
        // Get new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        // Get current scene
        Stage driverScene = (Stage) label1.getScene().getWindow();
        // Load new scene
        driverScene.setScene(loader.load());
    }

    @FXML // Method runs when ride requests button is clicked
    private void onRequestButton() throws IOException {
        // Get new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DriverRequest.fxml"));
        // Get current scene
        Stage driverScene = (Stage) label1.getScene().getWindow();
        // Load new scene
        driverScene.setScene(loader.load());
    }

    @FXML // Method runs when logout button is clicked
    private void onLogoutButton() throws IOException {
        // Get new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        // Get current scene
        Stage driverScene = (Stage) label1.getScene().getWindow();
        // Load new scene
        driverScene.setScene(loader.load());
    }
}
