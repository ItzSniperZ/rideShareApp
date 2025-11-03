package org.example.rideshareapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MapController {

    @FXML private TextField addressField;
    @FXML private TextField labelField;
    @FXML private Label statusLabel;

    @FXML
    private void onAddLocation() {
        Main.MAP_SERVICE.addLocation(addressField.getText(), labelField.getText());
        statusLabel.setText("Location added (stub).");
        addressField.clear();
        labelField.clear();
    }
}
