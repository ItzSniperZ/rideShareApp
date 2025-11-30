package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.rideshareapp.Main;

/**
 * Controller class for managing the main user interface of the RideShare application.
 * <p>
 * This controller is responsible for loading and switching between the different
 * content views of the application (Profile, Ride Request, and Map pages) using
 * a central {@link StackPane} as the container.
 * </p>
 *
 * <p><strong>FXML Mapped Elements:</strong></p>
 * <ul>
 *     <li>{@code contentPane} — The main container pane used to dynamically load and display pages.</li>
 * </ul>
 *
 * <p><strong>Navigation Overview:</strong></p>
 * <ul>
 *     <li>{@link #showProfile()} — Displays the user's profile page.</li>
 *     <li>{@link #showRideRequest()} — Displays the ride request interface.</li>
 *     <li>{@link #showMap()} — Displays the map view (placeholder or live map view).</li>
 *     <li>{@link #logout()} — Logs out the user and returns to the login screen.</li>
 * </ul>
 */
public class MainController {

    /** Primary container pane that holds the dynamically loaded FXML views. */
    @FXML
    private StackPane contentPane;

    /** Reference to the main application instance used for navigation. */
    private Main mainApp;

    /**
     * Sets the main application reference.
     * <p>
     * This method is called by the {@link Main} class to initialize the controller
     * and immediately display the profile page as the default view.
     * </p>
     *
     * @param mainApp the primary {@link Main} application instance
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        // show profile by default
        showProfile();
    }

    /**
     * Loads and displays the user's Profile page.
     * <p>
     * Invokes {@link #setContent(String)} with the FXML path for the profile view.
     * </p>
     */
    @FXML
    public void showProfile() {
        setContent("/org/example/rideshareapp/ProfilePage.fxml");
    }

    /**
     * Loads and displays the Ride Request page.
     * <p>
     * Invokes {@link #setContent(String)} with the FXML path for the ride request view.
     * </p>
     */
    @FXML
    public void showRideRequest() {
        setContent("/org/example/rideshareapp/RideRequestPage.fxml");
    }

    /**
     * Loads and displays the Map page.
     * <p>
     * Invokes {@link #setContent(String)} with the FXML path for the map view.
     * </p>
     */
    @FXML
    public void showMap() {
        setContent("/org/example/rideshareapp/MapPage.fxml");
    }

    /**
     * Logs out the current user and returns to the login page.
     * <p>
     * Calls {@link Main#showLogin()} to reload the login interface.
     * </p>
     */
    @FXML
    public void logout() {
        try {
            Stage window = (Stage) contentPane.getScene().getWindow();
            Main.getInstance().logout(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces the current content in {@link #contentPane} with a new FXML view.
     * <p>
     * Loads an FXML file dynamically from the provided path and replaces all
     * existing nodes within the content pane. This is used by navigation buttons
     * to switch between pages without changing scenes.
     * </p>
     *
     * @param fxmlPath the path to the FXML resource (e.g., {@code /org/example/rideshareapp/ProfilePage.fxml})
     */
    private void setContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
