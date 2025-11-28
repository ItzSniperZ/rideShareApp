package org.example.rideshareapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.rideshareapp.Main;

/**
 * Controller class for managing the user's profile within the RideShare application.
 * <p>
 * This controller handles profile updates, support requests, and payment method management.
 * It connects the UI form fields to the corresponding backend services for
 * {@link org.example.rideshareapp.services.ProfileService} and
 * {@link org.example.rideshareapp.services.PaymentService}.
 * </p>
 *
 * <p><strong>FXML Mapped Elements:</strong></p>
 * <ul>
 *     <li>{@code usernameField} — Text input for the user's username.</li>
 *     <li>{@code passwordField} — Password input for the user's password.</li>
 *     <li>{@code phoneField} — Text input for the user's phone number.</li>
 *     <li>{@code cardField} — Text input for the payment card number.</li>
 *     <li>{@code cvvField} — Text input for the card CVV code.</li>
 *     <li>{@code expField} — Text input for the card expiration date.</li>
 *     <li>{@code statusLabel} — Label displaying general status messages.</li>
 *     <li>{@code paymentStatus} — Label displaying feedback on payment actions.</li>
 * </ul>
 *
 * <p><strong>Features:</strong></p>
 * <ul>
 *     <li>Allows updating profile credentials and phone number.</li>
 *     <li>Submits support tickets directly from the profile page.</li>
 *     <li>Adds new payment methods and validates inputs before submission.</li>
 * </ul>
 */
public class ProfileController {

    /** Text field for entering the username. */
    @FXML private TextField usernameField;

    /** Password field for entering the account password. */
    @FXML private PasswordField passwordField;

    @FXML private TextField classificationField;

    /** Text field for entering the card number. */
    @FXML private TextField cardField;

    /** Text field for entering the CVV code of the payment card. */
    @FXML private TextField cvvField;

    /** Text field for entering the expiration date of the payment card. */
    @FXML private TextField expField;

    /** Label for displaying general profile status updates. */
    @FXML private Label statusLabel;

    /** Label for displaying payment method operation results. */
    @FXML private Label paymentStatus;

    /**
     * Triggered when the user clicks the “Update Profile” button.
     * <p>
     * Attempts to update the user's profile using
     * {@link org.example.rideshareapp.services.ProfileService#updateProfile(String, String, String)}.
     * If the phone number field is not numeric, an error message is displayed.
     * </p>
     */
    @FXML
    private void onUpdateProfile() {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();
        String classification = classificationField.getText().trim();

        if (user.isEmpty() || pass.isEmpty() || classification.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        boolean ok = Main.PROFILE_SERVICE.updateProfile(user, pass, classification);

        if (ok) {
            statusLabel.setText("Profile updated successfully.");
        } else {
            statusLabel.setText("Update failed. No matching user ID.");
        }
    }


    /**
     * Triggered when the user clicks the “Contact Support” button.
     * <p>
     * Submits a general support ticket through
     * {@link org.example.rideshareapp.services.ProfileService#contactSupport(String, String)}
     * and displays a confirmation message.
     * </p>
     */
    @FXML
    private void onContactSupport() {
        Main.PROFILE_SERVICE.contactSupport("General", "User opened support from profile.");
        statusLabel.setText("Support ticket submitted (stub).");
    }

    /**
     * Triggered when the user clicks the “Add Payment” button.
     * <p>
     * Attempts to add a payment method using
     * {@link org.example.rideshareapp.services.PaymentService#addPaymentMethod(long, long, String)}.
     * Displays success or error feedback depending on validation results.
     * </p>
     */
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
