package org.example.rideshareapp.services;

/**
 * Service class responsible for managing user profile data and
 * authentication within the RideShare application.
 * <p>
 * The {@code ProfileService} class provides user login/logout functionality,
 * basic profile updates (username, password, and phone number), and
 * a placeholder for contacting customer support.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Authenticate and manage user session state.</li>
 *   <li>Store and update basic user account information.</li>
 *   <li>Simulate support ticket submission for testing purposes.</li>
 * </ul>
 *
 * <p>
 * This implementation is simplified and stores user data in memory.
 * In a production environment, this would be replaced with database-backed
 * persistence and secure authentication mechanisms.
 * </p>
 */
public class ProfileService {

    /** Tracks whether the user is currently logged in. */
    private boolean loggedIn = false;

    /** The currently logged-in user's username (temporary placeholder). */
    private String username = "test";

    /** The user's password. */
    private String password = "test";

    /** The user's registered phone number. */
    private long phoneNumber = 0L;

    /**
     * Attempts to log the user in with the provided credentials.
     * <p>
     * In this simplified version, any non-empty username and password
     * combination is considered valid.
     * </p>
     *
     * @param username the username to authenticate
     * @param password the password for the user
     * @return {@code true} if credentials are non-empty and login succeeds;
     *         {@code false} otherwise
     */
    public boolean login(String username, String password) {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    /**
     * Logs the user out and resets the authentication state.
     *
     * @return {@code true} once the logout operation completes
     */
    public boolean logout() {
        this.loggedIn = false;
        return true;
    }

    /**
     * Updates the stored username if the provided value is valid.
     *
     * @param newUsername the new username to set
     */
    public void updateUsername(String newUsername) {
        if (newUsername != null && !newUsername.isEmpty()) {
            this.username = newUsername;
        }
    }

    /**
     * Updates the stored password if the provided value is valid.
     *
     * @param newPassword the new password to set
     */
    public void updatePassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    /**
     * Updates the stored phone number.
     *
     * @param newPhoneNumber the new phone number to associate with the user
     */
    public void updatePhoneNumber(long newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    /**
     * Updates multiple fields in the user profile at once.
     * <p>
     * Only non-null, non-empty values (and non-zero phone numbers)
     * will be applied to the stored user record.
     * </p>
     *
     * @param username the new username, or {@code null} to leave unchanged
     * @param password the new password, or {@code null} to leave unchanged
     * @param phoneNumber the new phone number, or {@code 0L} to leave unchanged
     */
    public void updateProfile(String username, String password, long phoneNumber) {
        if (username != null && !username.isEmpty()) updateUsername(username);
        if (password != null && !password.isEmpty()) updatePassword(password);
        if (phoneNumber != 0L) updatePhoneNumber(phoneNumber);
    }

    /**
     * Simulates submitting a support ticket to customer service.
     * <p>
     * This method prints a ticket message to the console for testing.
     * In a real deployment, it could send the data to a remote support API.
     * </p>
     *
     * @param reason a short description of the issue type
     * @param description a detailed explanation of the problem
     */
    public void contactSupport(String reason, String description) {
        System.out.println("Support ticket -> " + reason + ": " + description);
    }
}
