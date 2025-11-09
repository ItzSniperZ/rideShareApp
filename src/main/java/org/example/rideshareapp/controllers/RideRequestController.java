package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.rideshareapp.Main;
import org.example.rideshareapp.services.MapService;

/**
 * Controller class responsible for managing the user interface
 * related to creating and submitting ride requests within the
 * RideShare application.
 * <p>
 * The {@code RideRequestController} coordinates between the view
 * (FXML interface) and the backend services including
 * {@link org.example.rideshareapp.services.MapService} and
 * {@link org.example.rideshareapp.services.RideRequestService}.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Display available saved locations to the user.</li>
 *   <li>Allow users to calculate ride cost based on distance and
 *       price per mile.</li>
 *   <li>Handle ride request submissions by invoking the
 *       {@code RideRequestService} through the {@code Main} service registry.</li>
 * </ul>
 *
 * <p>
 * This class acts as the “Controller” in the MVC architecture,
 * bridging between the FXML view and the service-layer “Model.”
 * </p>
 */
public class RideRequestController {

    /** ComboBox allowing the user to select their current location. */
    @FXML private ComboBox<MapService.Location> currentLocationBox;

    /** ComboBox allowing the user to select their ride destination. */
    @FXML private ComboBox<MapService.Location> destinationBox;

    /** TextField for entering the price per mile rate. */
    @FXML private TextField pricePerMileField;

    /** Label displaying the calculated distance between two locations. */
    @FXML private Label distanceLabel;

    /** Label displaying the estimated cost of the ride. */
    @FXML private Label costLabel;

    /** Button used to submit the ride request once calculated. */
    @FXML private Button requestRideBtn;

    /** Label for displaying feedback and status messages to the user. */
    @FXML private Label statusLabel;

    /**
     * Initializes the controller by populating location ComboBoxes
     * with saved locations retrieved from {@link MapService}.
     * <p>
     * This method is automatically called after the FXML components
     * are loaded and injected by the JavaFX runtime.
     * </p>
     */
    @FXML
    private void initialize() {
        currentLocationBox.getItems().addAll(Main.MAP_SERVICE.getSavedLocations());
        destinationBox.getItems().addAll(Main.MAP_SERVICE.getSavedLocations());
    }

    /**
     * Calculates the ride distance and estimated cost based on
     * the selected origin and destination.
     * <p>
     * The method retrieves distance from {@link MapService#calculateDistance}
     * and computes total cost using {@link MapService#calculateCost}.
     * </p>
     * <p>
     * If valid inputs are provided, the ride request button is enabled
     * for the user to proceed.
     * </p>
     */
    @FXML
    private void onCalculate() {
        MapService.Location from = currentLocationBox.getValue();
        MapService.Location to = destinationBox.getValue();

        if (from == null || to == null) {
            statusLabel.setText("Select both locations.");
            return;
        }

        double distance = Main.MAP_SERVICE.calculateDistance(from, to);
        distanceLabel.setText("Distance: " + distance + " miles");

        double ppm = 3.5;
        try {
            ppm = Double.parseDouble(pricePerMileField.getText());
        } catch (NumberFormatException ignored) {
        }

        double cost = Main.MAP_SERVICE.calculateCost(distance, ppm);
        costLabel.setText("Cost: $" + String.format("%.2f", cost));

        requestRideBtn.setDisable(false);
        statusLabel.setText("");
    }

    /**
     * Submits the ride request to the backend service layer.
     * <p>
     * This method gathers ride details, retrieves the first valid
     * payment method from {@link org.example.rideshareapp.services.PaymentService},
     * and calls {@link org.example.rideshareapp.services.RideRequestService#requestRide}.
     * </p>
     * <p>
     * The status label is updated to indicate whether the ride request
     * succeeded or failed.
     * </p>
     */
    @FXML
    private void onRequestRide() {
        MapService.Location to = destinationBox.getValue();
        MapService.Location from = currentLocationBox.getValue();

        double distance = Main.MAP_SERVICE.calculateDistance(from, to);
        double cost = Main.MAP_SERVICE.calculateCost(
                distance,
                Double.parseDouble(pricePerMileField.getText())
        );

        boolean ok = Main.RIDE_REQUEST_SERVICE.requestRide(
                to,
                cost,
                distance,
                Main.PAYMENT_SERVICE.getFirstPaymentMethod()
        );

        statusLabel.setText(ok
                ? "Ride requested (stub)."
                : "Ride request failed (no valid payment?)");
    }
}
