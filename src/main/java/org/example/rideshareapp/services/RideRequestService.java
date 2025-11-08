package org.example.rideshareapp.services;

public class RideRequestService {

    private final PaymentService paymentService;
    private final MapService mapService;

    public RideRequestService(PaymentService paymentService, MapService mapService) {
        this.paymentService = paymentService;
        this.mapService = mapService;
    }

    public boolean requestRide(MapService.Location destination,
                               double rideCost,
                               double rideDistance,
                               PaymentService.PaymentMethod card) {
        if (card == null) return false;
        return paymentService.processPayment(card, rideCost);
    }

    public void cancelRideRequest(Object rideRequest) {
        // stub
    }
}
