package org.example.rideshareapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField phoneField;

    @FXML
    private TextField cardField;
    @FXML
    private TextField cvvField;
    @FXML
    private TextField expField;

    @FXML
    private Label statusLabel;
    @FXML
    private Label paymentStatus;

    @FXML
    private void onUpdateProfile() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        long phone = 0L;

        try {
            if (!phoneField.getText().isEmpty()) {
                phone = Long.parseLong(phoneField.getText());
            }
            Main.PROFILE_SERVICE.updateProfile(user, pass, phone);
            statusLabel.setText("Profile updated (stub).");
        } catch (NumberFormatException e) {
            statusLabel.setText("Phone must be numeric.");
        }
    }

    @FXML
    private void onContactSupport() {
        Main.PROFILE_SERVICE.contactSupport("General", "User opened support from profile.");
        statusLabel.setText("Support ticket submitted (stub).");
    }

    @FXML
    private void onAddPayment() {
        try {
            long cardNum = Long.parseLong(cardField.getText());
            long cvv = Long.parseLong(cvvField.getText());
            String exp = expField.getText();

            boolean ok = Main.PAYMENT_SERVICE.addPaymentMethod(cardNum, cvv, exp);
            paymentStatus.setText(ok ? "Payment added." : "Payment invalid.");
        } catch (NumberFormatException e) {
            paymentStatus.setText("Card/CVV must be numeric.");
        }
    }
}
