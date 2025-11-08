package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.rideshareapp.Main;
import org.example.rideshareapp.services.MapService;

public class RideRequestController {

    @FXML private ComboBox<MapService.Location> currentLocationBox;
    @FXML private ComboBox<MapService.Location> destinationBox;
    @FXML private TextField pricePerMileField;
    @FXML private Label distanceLabel;
    @FXML private Label costLabel;
    @FXML private Button requestRideBtn;
    @FXML private Label statusLabel;

    @FXML
    private void initialize() {
        // load saved locations from map service
        currentLocationBox.getItems().addAll(Main.MAP_SERVICE.getSavedLocations());
        destinationBox.getItems().addAll(Main.MAP_SERVICE.getSavedLocations());
    }

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
        } catch (NumberFormatException ignored) {}
        double cost = Main.MAP_SERVICE.calculateCost(distance, ppm);
        costLabel.setText("Cost: $" + String.format("%.2f", cost));

        requestRideBtn.setDisable(false);
        statusLabel.setText("");
    }

    @FXML
    private void onRequestRide() {
        MapService.Location to = destinationBox.getValue();
        MapService.Location from = currentLocationBox.getValue();

        double distance = Main.MAP_SERVICE.calculateDistance(from, to);
        double cost = Main.MAP_SERVICE.calculateCost(distance, Double.parseDouble(pricePerMileField.getText()));

        boolean ok = Main.RIDE_REQUEST_SERVICE.requestRide(
                to,
                cost,
                distance,
                Main.PAYMENT_SERVICE.getFirstPaymentMethod()
        );

        statusLabel.setText(ok ? "Ride requested (stub)." : "Ride request failed (no valid payment?)");
    }
}
