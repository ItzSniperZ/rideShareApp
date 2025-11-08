package org.example.rideshareapp.services;

import java.util.ArrayList;

public class MapService {

    public static class Location {
        String address;
        String label;

        public Location(String address, String label) {
            this.address = address;
            this.label = label;
        }

        @Override
        public String toString() {
            return label + " (" + address + ")";
        }
    }

    private ArrayList<Location> savedLocations = new ArrayList<>();

    public MapService() {
        savedLocations.add(new Location("131 Park Ave", "School"));
        savedLocations.add(new Location("22 Main St", "Home"));
        savedLocations.add(new Location("44 State Rd", "Work"));
    }

    public void addLocation(String address, String label) {
        savedLocations.add(new Location(address, label));
    }

    public void removeLocation(Location selectedLocation) {
        savedLocations.remove(selectedLocation);
    }

    public void updateLocation(Location selectedLocation, String address, String label) {
        if (selectedLocation != null) {
            selectedLocation.address = address;
            selectedLocation.label = label;
        }
    }

    public void selectDestination(Location currentLocation, Location selectedLocation) {
        // stub
    }

    public void updateDestination(Location destination, Location selectedLocation) {
        // stub
    }

    public double calculateDistance(Location currentLocation, Location destination) {
        return 5.3; // fake for now
    }

    public double calculateCost(double rideDistance, double pricePerMile) {
        return rideDistance * pricePerMile;
    }

    public ArrayList<Location> getSavedLocations() {
        return savedLocations;
    }
}

