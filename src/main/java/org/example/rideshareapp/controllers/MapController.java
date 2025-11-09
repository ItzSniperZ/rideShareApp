package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.rideshareapp.Main;

/**
 * Controller class for managing map-related interactions within the RideShare application.
 * <p>
 * This class handles user input for adding and labeling saved locations through
 * the application's {@link org.example.rideshareapp.services.MapService}.
 * It provides a simple interface for entering an address and an optional label,
 * then updates the status field to confirm the action.
 * </p>
 *
 * <p><strong>FXML Mapped Elements:</strong></p>
 * <ul>
 *     <li>{@code addressField} — Text input for entering the location address.</li>
 *     <li>{@code labelField} — Text input for labeling the location (e.g., “Home”, “Work”).</li>
 *     <li>{@code statusLabel} — Label for displaying confirmation or feedback messages.</li>
 * </ul>
 */
public class MapController {

    /** Text field for entering the location’s address. */
    @FXML private TextField addressField;

    /** Text field for entering a custom label for the location. */
    @FXML private TextField labelField;

    /** Label for displaying success or error messages related to map actions. */
    @FXML private Label statusLabel;

    /**
     * Triggered when the user clicks the “Add Location” button.
     * <p>
     * Calls {@link org.example.rideshareapp.services.MapService#addLocation(String, String)}
     * to add the specified address and label to the user’s saved locations.
     * After the operation, the input fields are cleared and a confirmation message is shown.
     * </p>
     */
    @FXML
    private void onAddLocation() {
        Main.MAP_SERVICE.addLocation(addressField.getText(), labelField.getText());
        statusLabel.setText("Location added (stub).");
        addressField.clear();
        labelField.clear();
    }
}
