package org.example.rideshareapp.services;

import org.example.rideshareapp.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles user authentication, account creation, profile updates,
 * and support actions within the RideShare application.
 * This service maintains basic session information for the logged-in user
 * and interacts with the underlying database to manage stored user data.
 */
public class ProfileService {

    /** The unique database ID of the currently logged-in user. */
    private int profileId;

    /** The username of the logged-in user. */
    private String username;

    /** The stored password hash or plain password value for the user. */
    private String password_hash;

    /** The user classification (e.g., Driver or Rider). */
    private String classification;

    /**
     * Registers a new user in the database.
     *
     * @param username the username to create
     * @param password the password to store
     * @param classification the user classification
     * @return true if registration succeeds, false otherwise
     */
    public boolean register(String username, String password, String classification) {
        String sql = "INSERT INTO users (username, password_hash, classification) VALUES (?, ?, ?)";

        try (Connection conn = DB.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, classification);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("[REGISTER ERROR] " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns the database ID of the current user.
     *
     * @return the logged-in user's profile ID
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * Attempts to authenticate a user using stored values in the users table.
     * If successful, the user's session information is loaded into memory.
     *
     * @param username the username provided at login
     * @param password the password provided at login
     * @param classification the user classification attempting to log in
     * @return true if the credentials match a stored user, false otherwise
     */
    public boolean login(String username, String password, String classification) {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ? AND classification = ?";

        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            ps.setString(3, classification.trim());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.profileId = rs.getInt("id");
                this.username = rs.getString("username");
                this.password_hash = rs.getString("password_hash");
                this.classification = rs.getString("classification");
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the stored account information for the currently logged-in user.
     *
     * @param newUsername the new username to apply
     * @param newPassword the new password value to store
     * @param newClassification the new classification value
     * @return true if the update succeeded, false if no rows were affected
     */
    public boolean updateProfile(String newUsername, String newPassword, String newClassification) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, classification = ? WHERE id = ?";

        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newUsername.trim());
            ps.setString(2, newPassword.trim());
            ps.setString(3, newClassification.trim());
            ps.setInt(4, this.profileId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                this.username = newUsername;
                this.password_hash = newPassword;
                this.classification = newClassification;
                return true;
            } else {
                System.err.println("[UPDATE FAILED] No rows updated.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("[UPDATE ERROR] " + e.getMessage());
            return false;
        }
    }

    /**
     * Prints profile information for a specific user ID.
     *
     * @param profileId the ID of the user whose profile should be displayed
     */
    public void viewProfile(int profileId) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, profileId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Classification: " + rs.getString("classification"));
                System.out.println("Password: " + rs.getString("password_hash"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Submits a simple support request to the console.
     *
     * @param type the category of the support request
     * @param message the message included with the support ticket
     */
    public void contactSupport(String type, String message) {
        System.out.println("[Support] (" + type + "): " + message);
    }
}
