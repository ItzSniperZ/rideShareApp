package org.example.rideshareapp.services;

import org.example.rideshareapp.db.DB;
import org.example.rideshareapp.controllers.LoginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private int profileId;
    private String username;
    private String password_hash;
    private String classification;

    public boolean register(String username, String password, String classification) {
        String sql = "INSERT INTO users (username, password_hash, classification) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            // parameter index 1 tells it to fill in the first placeholder which is represented as ?
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, classification);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); //error logging
            return false;
        }
    }

    public boolean login() { //needs classification
        // Select retries data from the database users  * is all columns where username and password both match the right values
        LoginController LoginController1 = new LoginController();
        String username = LoginController1.getUsername();
        String password = LoginController1.getPassword();
        String classification = LoginController1.getClassification() ;

        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ? AND classification = ?";
        PreparedStatement preparedStatement = null;
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            ps.setString(3, classification.trim());
            ResultSet rs = ps.executeQuery(); //A mini table in Java that holds the rows your query returned
            if (rs.next()) {
                // loads these fields into the object once logged in
                this.profileId = rs.getInt("id"); // I think the column labels need to match what's in the database
                this.username = rs.getString("username");
                this.password_hash = rs.getString("password_hash");
                this.classification = rs.getString("classification");
                //this.phoneNumber = rs.getLong("phone_number");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public void updateProfile(String username, String password, String classification) {
        String sql = "UPDATE users SET classification = ? WHERE username = ? AND  password_hash = ?";
        PreparedStatement preparedStatement = null;
        try(Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, classification);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void viewProfile(int profileId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try(Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
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
}
