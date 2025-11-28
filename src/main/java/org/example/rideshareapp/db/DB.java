package org.example.rideshareapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides database initialization and access for the RideShare application.
 * This class creates the required SQLite tables on first use and exposes
 * a method for retrieving active database connections.
 *
 * <p>The database uses a local SQLite file located in the application's
 * {@code data} directory. Tables are created automatically if they do not
 * already exist.</p>
 */
public class DB {

    /** Path to the SQLite database file used by the application. */
    private static final String URL = "jdbc:sqlite:./data/app.db";

    /**
     * Static initializer that prepares the database when the class is loaded.
     * This includes enabling SQLite settings and creating the required tables.
     * Any initialization failure results in a runtime exception.
     */
    static {
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {

            s.execute("PRAGMA foreign_keys = ON");
            s.execute("PRAGMA journal_mode = WAL");
            s.execute("PRAGMA synchronous = NORMAL");

            s.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  classification TEXT NOT NULL DEFAULT ' ',
                  username TEXT NOT NULL UNIQUE,
                  password_hash TEXT NOT NULL,
                  created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ','now'))
                )
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS drivers (
                  driverId INTEGER PRIMARY KEY,
                  plateNumber TEXT NOT NULL,
                  availabilityStatus INTEGER NOT NULL DEFAULT 0,
                  created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ','now')),
                  FOREIGN KEY (driverId) REFERENCES users(id)
                )
            """);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database!", e);
        }
    }

    /**
     * Returns an active connection to the SQLite database.
     *
     * @return a new {@link Connection} instance linked to the database
     * @throws SQLException if a connection cannot be established
     */
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
