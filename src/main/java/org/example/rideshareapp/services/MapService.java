package org.example.rideshareapp.services;

import java.util.ArrayList;

/**
 * Service class responsible for managing map-related data and operations
 * within the RideShare application.
 * <p>
 * The {@code MapService} class provides storage and manipulation of
 * {@link Location} objects, representing user-saved addresses such as
 * “Home,” “Work,” or “School.” It also contains basic methods for
 * calculating distances and ride costs between two locations.
 * </p>
 *
 * <p>This class currently uses static, simulated data for demonstration
 * purposes. Future versions could integrate real mapping APIs such as
 * Google Maps or OpenStreetMap for distance and routing calculations.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Manage a list of saved {@link Location} objects.</li>
 *   <li>Provide methods to add, remove, and update location data.</li>
 *   <li>Compute (mock) ride distance and cost estimates.</li>
 *   <li>Support integration with {@link org.example.rideshareapp.controllers.MapController}
 *       and {@link org.example.rideshareapp.controllers.RideRequestController}.</li>
 * </ul>
 */
public class MapService {

    /**
     * Represents a saved location (e.g., Home, Work, School) within the
     * RideShare system.
     */
    public static class Location {
        /** The full street address of the location. */
        String address;

        /** A short user-assigned label for the location. */
        String label;

        /**
         * Constructs a new {@code Location} object.
         *
         * @param address the street address of the location
         * @param label a short, user-defined label (e.g., “Home”)
         */
        public Location(String address, String label) {
            this.address = address;
            this.label = label;
        }

        /**
         * Returns a human-readable representation of this location.
         *
         * @return the label and address combined into a formatted string
         */
        @Override
        public String toString() {
            return label + " (" + address + ")";
        }
    }

    /** The in-memory list of saved user locations. */
    private ArrayList<Location> savedLocations = new ArrayList<>();

    /**
     * Constructs the {@code MapService} and initializes it with
     * a few pre-defined sample locations.
     */
    public MapService() {
        savedLocations.add(new Location("131 Park Ave", "School"));
        savedLocations.add(new Location("22 Main St", "Home"));
        savedLocations.add(new Location("44 State Rd", "Work"));
    }

    /**
     * Adds a new location to the saved locations list.
     *
     * @param address the address of the location
     * @param label a short, user-assigned label
     */
    public void addLocation(String address, String label) {
        savedLocations.add(new Location(address, label));
    }

    /**
     * Removes a previously saved location.
     *
     * @param selectedLocation the {@link Location} to be removed
     */
    public void removeLocation(Location selectedLocation) {
        savedLocations.remove(selectedLocation);
    }

    /**
     * Updates the address and label of an existing location.
     *
     * @param selectedLocation the location to modify
     * @param address the new address
     * @param label the new label
     */
    public void updateLocation(Location selectedLocation, String address, String label) {
        if (selectedLocation != null) {
            selectedLocation.address = address;
            selectedLocation.label = label;
        }
    }

    /**
     * Placeholder for selecting a ride destination.
     * <p>Currently a stub method with no implementation.</p>
     *
     * @param currentLocation the user’s current location
     * @param selectedLocation the chosen destination
     */
    public void selectDestination(Location currentLocation, Location selectedLocation) {
        //placeholder method for future implementation
    }

    /**
     * Placeholder for updating a previously selected destination.
     * <p>Currently a stub method with no implementation.</p>
     *
     * @param destination the current destination
     * @param selectedLocation the new selected location
     */
    public void updateDestination(Location destination, Location selectedLocation) {
        //placeholder method for future implementation
    }

    /**
     * Calculates the approximate distance between two locations.
     * <p>
     * Currently returns a fixed simulated value (5.3 miles).
     * </p>
     *
     * @param currentLocation the user’s starting location
     * @param destination the desired destination location
     * @return the simulated ride distance in miles
     */
    public double calculateDistance(Location currentLocation, Location destination) {
        return 5.3; // simulated for demonstration
    }

    /**
     * Calculates the estimated cost of a ride.
     *
     * @param rideDistance the total ride distance in miles
     * @param pricePerMile the cost per mile in USD
     * @return the total estimated cost of the ride
     */
    public double calculateCost(double rideDistance, double pricePerMile) {
        return rideDistance * pricePerMile;
    }

    /**
     * Retrieves the list of saved user locations.
     *
     * @return an {@link ArrayList} of {@link Location} objects
     */
    public ArrayList<Location> getSavedLocations() {
        return savedLocations;
    }
}
