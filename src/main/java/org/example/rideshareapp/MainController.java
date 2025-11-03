package org.example.rideshareapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane contentPane;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        // show profile by default
        showProfile();
    }

    @FXML
    public void showProfile() {
        setContent("/org/example/rideshareapp/ProfilePage.fxml");
    }

    @FXML
    public void showRideRequest() {
        setContent("/org/example/rideshareapp/RideRequestPage.fxml");
    }

    @FXML
    public void showMap() {
        setContent("/org/example/rideshareapp/MapPage.fxml");
    }

    @FXML
    public void logout() {
        try {
            mainApp.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath)
            );
            Node view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
