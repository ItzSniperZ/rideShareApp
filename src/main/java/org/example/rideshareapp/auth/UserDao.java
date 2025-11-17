package org.example.rideshareapp.auth;

import org.example.rideshareapp.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // Register a new user (plain-text password for this project)
    public boolean createUser(String username, String plainPassword, String classification) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash, classification) VALUES (?, ?, ?)";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim().toLowerCase());
            ps.setString(2, plainPassword);  // store plain text now
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // If username is already taken (UNIQUE constraint), return false instead of crashing
            if (e.getMessage() != null && e.getMessage().contains("UNIQUE")) {
                return false;
            }
            throw e;
        }
    }

    // Check credentials by comparing plain-text password
    public boolean checkCredentials(String username, String plainPassword) throws SQLException {
        String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return false; // no such user
                }
                String storedPassword = rs.getString("password_hash");
                return plainPassword.equals(storedPassword); // plain-text comparison
            }
        }
    }

    // Adds a demo user the first time you run the app
    public void ensureSeedUser(String user, String pass, String classification) throws SQLException {
        String check = "SELECT 1 FROM users WHERE username = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(check)) {
            ps.setString(1, user.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    createUser(user, pass, classification); // will store plain-text now
                }
            }
        }
    }
}

