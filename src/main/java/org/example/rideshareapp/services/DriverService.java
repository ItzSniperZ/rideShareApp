package org.example.rideshareapp.services;

import org.example.rideshareapp.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverService extends ProfileService {
    //They need to be paid
    private Integer driverId;
    private String plateNumber;
    private boolean availabilityStatus; // 0= not available 1= available

    //needs to load the drivers info from the database onto the driver
    public void loadDrivers() {

    }
    public void setPlateNumber(String plateNumber, Integer driverId) {
        String sql = "UPDATE drivers SET plateNumber = ? WHERE driverId = ?";
        PreparedStatement preparedStatement = null;
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, plateNumber);
            ps.setInt(2, driverId);
            ps.executeUpdate();
            this.plateNumber = plateNumber;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //im storing availabilityStatus as an int so im not sure what this does
    public void toggleAvailability(boolean availabilityStatus, Integer driverId) {
        this.availabilityStatus = !this.availabilityStatus; //flip availabilityStatus
        String sql = "UPDATE drivers SET availabilityStatus = ? WHERE driverId = ?";
        PreparedStatement preparedStatement = null;
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setBoolean(1, availabilityStatus);
            ps.setInt(2, driverId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptRide() {
    }

    public void completeRide() {
    }
}
