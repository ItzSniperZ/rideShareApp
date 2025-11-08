package org.example.rideshareapp.auth;

import org.example.rideshareapp.db.DB;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    // Register a new user
    public boolean createUser(String username, String plainPassword) throws SQLException {
        String hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim().toLowerCase());
            ps.setString(2, hash);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("UNIQUE")) {
                return false; // username already exists
            }
            throw e;
        }
    }

    // Login check
    public boolean checkCredentials(String username, String plainPassword) throws SQLException {
        String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;
                String storedHash = rs.getString("password_hash");
                return BCrypt.checkpw(plainPassword, storedHash);
            }
        }
    }

    // Adds a demo user the first time you run the app
    public void ensureSeedUser(String user, String pass) throws SQLException {
        String check = "SELECT 1 FROM users WHERE username = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(check)) {
            ps.setString(1, user.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    createUser(user, pass);
                }
            }
        }
    }
}
