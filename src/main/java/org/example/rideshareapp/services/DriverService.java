package org.example.rideshareapp.services;

import org.example.rideshareapp.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverService extends ProfileService {
    private String plateNumber;
    private boolean availabilityStatus; // 0= not available 1= available


    public void setPlateNumber(String plateNumber, Integer driverId) {
        String sql = "UPDATE drivers SET plateNumber = ? WHERE driverId = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, plateNumber);
            ps.setInt(2, driverId);
            ps.executeUpdate();
            this.plateNumber = plateNumber;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //im storing availabilityStatus as an int
    public void toggleAvailability(Integer driverId) {
        // 1. Get current status from DB
        boolean currentStatus;
        String selectSql = "SELECT availabilityStatus FROM drivers WHERE driverId = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(selectSql)) {
            ps.setInt(1, driverId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentStatus = rs.getInt("availabilityStatus") == 1; //availabilityStatus is stored as a
                // int 1 being true 0 being false so this line is checking if its true or false and saving it in current status

            } else {
                throw new RuntimeException("Driver not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean newStatus = !currentStatus; //flips status
        int statusInt; // makes it possible to store in db
        if (newStatus) {
            statusInt = 1;
        } else {
            statusInt = 0;
        }
        String updateSql = "UPDATE drivers SET availabilityStatus = ? WHERE driverId = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(updateSql)) {
            ps.setInt(1, statusInt);
            ps.setInt(2, driverId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Accept a ride → driver unavailable
    public void acceptRide(int driverId) {
        this.availabilityStatus = false; // unavailable

        String sql = "UPDATE drivers SET availabilityStatus = 0 WHERE driverId = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, driverId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Complete a ride → driver available
    public void completeRide(int driverId) {
        this.availabilityStatus = true; // available

        String sql = "UPDATE drivers SET availabilityStatus = 1 WHERE driverId = ?";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, driverId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
