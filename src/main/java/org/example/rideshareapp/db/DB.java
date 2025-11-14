package org.example.rideshareapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    // Where the database file will be stored (inside your 'data' folder)
    private static final String URL = "jdbc:sqlite:./data/app.db";

    static {
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {

            //SQLite settings
            s.execute("PRAGMA foreign_keys = ON");
            s.execute("PRAGMA journal_mode = WAL");
            s.execute("PRAGMA synchronous = NORMAL");

            // Create users table if it doesn't already exist
            s.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT NOT NULL UNIQUE,
                  password_hash TEXT NOT NULL,
                  created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ','now'))
                )
            """);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database!", e);
        }
    }

    // Method that returns a connection to the database when needed
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
