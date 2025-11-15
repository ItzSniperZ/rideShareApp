package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;


public class DriverRequestController {
@FXML private TextField requestText;

@FXML private Text text1;

@FXML private Button acceptButton;

@FXML private Button rejectButton;

@FXML
    private void onAcceptButton() throws IOException {
        requestText.clear(); // This will also take you to another page, but I think we need the map for that
}

@FXML private void onRejectButton() throws IOException {
    requestText.clear();
}
}
