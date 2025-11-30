package org.example.rideshareapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.rideshareapp.Main;

/**
 * Controller class for handling navigation actions within the driver interface.
 * <p>
 * This controller manages transitions from the driver dashboard to the
 * profile page, ride request page, and login screen.
 * </p>
 */
public class DriverController {

    /**
     * Opens the profile page when the profile button is clicked.
     *
     * @param event the originating action event
     */
    @FXML
    private void onProfileButton(ActionEvent event) {
        switchScene(event, "/org/example/rideshareapp/ProfilePage.fxml");
    }

    /**
     * Opens the driver ride request page when the request button is clicked.
     *
     * @param event the originating action event
     */
    @FXML
    private void onRequestButton(ActionEvent event) {
        switchScene(event, "/org/example/rideshareapp/DriverRequest.fxml");
    }

    /**
     * Returns the user to the login screen when the logout button is clicked.
     *
     * @param event the originating action event
     */
    @FXML
    private void onLogoutButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main.getInstance().logoutToLogin(window);
    }

    /**
     * Loads and displays a new scene based on the provided FXML path.
     *
     * @param event    the event that triggered the scene change
     * @param fxmlPath the path to the FXML file to load
     */
    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
