package org.example.rideshareapp.services;

/**
 * Service class responsible for managing ride requests within the
 * RideShare application.
 * <p>
 * The {@code RideRequestService} coordinates ride creation, cost
 * validation, and payment authorization by integrating with both
 * {@link PaymentService} and {@link MapService}. This implementation
 * is a simplified version that simulates backend ride processing.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Coordinate between payment and mapping services when a ride is requested.</li>
 *   <li>Validate ride request parameters such as destination, cost, and payment method.</li>
 * </ul>
 *
 * <p>
 * In a production system, this service would communicate with a backend
 * server, handle driver assignment, and track ride status updates.
 * </p>
 */
public class RideRequestService {

    /** Reference to the application's payment service for handling ride payments. */
    private final PaymentService paymentService;

    /** Reference to the application's map service for location and route management. */
    private final MapService mapService;

    /**
     * Constructs a new {@code RideRequestService} instance with
     * dependencies on the {@link PaymentService} and {@link MapService}.
     *
     * @param paymentService the payment service used for validating and processing ride charges
     * @param mapService the map service used for managing ride destinations and distances
     */
    public RideRequestService(PaymentService paymentService, MapService mapService) {
        this.paymentService = paymentService;
        this.mapService = mapService;
    }

    /**
     * Submits a new ride request to the system.
     * <p>
     * This method validates the provided payment method and simulates
     * a successful payment transaction for the ride.
     * </p>
     *
     * @param destination the {@link MapService.Location} where the user wishes to travel
     * @param rideCost the total ride cost in USD
     * @param rideDistance the total ride distance in miles
     * @param card the {@link PaymentService.PaymentMethod} used for the transaction
     * @return {@code true} if the payment was processed successfully;
     *         {@code false} if the card is invalid or the transaction failed
     */
    public boolean requestRide(MapService.Location destination,
                               double rideCost,
                               double rideDistance,
                               PaymentService.PaymentMethod card) {
        if (card == null) return false;
        return paymentService.processPayment(card, rideCost);
    }

    /**
     * Cancels an active ride request.
     * <p>
     * This is a placeholder method for future implementation. In a
     * production system, it would update the ride’s backend status
     * and initiate a refund if applicable.
     * </p>
     *
     * @param rideRequest an identifier or object representing the current ride request
     */
    public void cancelRideRequest(Object rideRequest) {
        ///placeholder for future implementation
    }
}
