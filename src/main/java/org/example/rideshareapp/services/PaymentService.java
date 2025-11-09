package org.example.rideshareapp.services;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Service class responsible for managing user payment information and
 * transaction processing within the RideShare application.
 * <p>
 * The {@code PaymentService} class stores, validates, and updates user
 * {@link PaymentMethod} objects. It performs basic validation for
 * credit/debit card details and simulates payment and refund transactions.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li>Store and manage user payment methods.</li>
 *   <li>Validate credit/debit card numbers, CVV codes, and expiration dates.</li>
 *   <li>Simulate payment and refund operations.</li>
 *   <li>Provide access to stored payment methods for other services
 *       such as {@link org.example.rideshareapp.services.RideRequestService}.</li>
 * </ul>
 *
 * <p>
 * This implementation is simplified for demonstration and testing purposes.
 * It does not handle secure storage or real payment API integration.
 * </p>
 *
 * @author
 *   Chris F.
 * @version 1.0
 * @since 2025-11
 */
public class PaymentService {

    /**
     * Represents a stored payment method, including card number,
     * CVV code, and expiration date.
     */
    public static class PaymentMethod {
        /** The 16-digit card number. */
        long cardNumber;

        /** The 3-digit Card Verification Value (CVV). */
        long cvv;

        /** The card's expiration date in {@code MM/yy} format. */
        String expirationDate;

        /**
         * Constructs a new {@code PaymentMethod} with the given details.
         *
         * @param cardNumber the 16-digit credit/debit card number
         * @param cvv the 3-digit Card Verification Value
         * @param expirationDate the expiration date formatted as {@code MM/yy}
         */
        public PaymentMethod(long cardNumber, long cvv, String expirationDate) {
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.expirationDate = expirationDate;
        }
    }

    /** List of all stored payment methods for the current user. */
    private ArrayList<PaymentMethod> methods = new ArrayList<>();

    /**
     * Adds a new payment method after performing basic validation checks.
     *
     * @param cardNumber a 16-digit card number
     * @param cardVerificationValue a 3-digit CVV code
     * @param expirationDate the expiration date formatted as {@code MM/yy}
     * @return {@code true} if the payment method was valid and added; {@code false} otherwise
     */
    public boolean addPaymentMethod(long cardNumber, long cardVerificationValue, String expirationDate) {
        if (String.valueOf(cardNumber).length() != 16) return false;
        if (String.valueOf(cardVerificationValue).length() != 3) return false;
        if (!isValidExpiration(expirationDate)) return false;

        methods.add(new PaymentMethod(cardNumber, cardVerificationValue, expirationDate));
        return true;
    }

    /**
     * Removes a saved payment method from the user's list.
     *
     * @param card the {@link PaymentMethod} to remove
     */
    public void removePaymentMethod(PaymentMethod card) {
        methods.remove(card);
    }

    /**
     * Updates an existing payment method with new validated details.
     *
     * @param card the {@link PaymentMethod} to update
     * @param newCN the new 16-digit card number
     * @param newCVV the new 3-digit CVV code
     * @param newExp the new expiration date in {@code MM/yy} format
     * @return {@code true} if the update succeeded; {@code false} if validation failed
     */
    public boolean updatePaymentMethod(PaymentMethod card, long newCN, long newCVV, String newExp) {
        if (card == null) return false;
        if (String.valueOf(newCN).length() != 16) return false;
        if (String.valueOf(newCVV).length() != 3) return false;
        if (!isValidExpiration(newExp)) return false;

        card.cardNumber = newCN;
        card.cvv = newCVV;
        card.expirationDate = newExp;
        return true;
    }

    /**
     * Simulates charging a card for a ride cost.
     *
     * @param card the payment method to charge
     * @param rideCost the total ride cost in USD
     * @return {@code true} if the transaction is valid and processed; {@code false} otherwise
     */
    public boolean processPayment(PaymentMethod card, double rideCost) {
        return card != null && rideCost > 0;
    }

    /**
     * Simulates refunding a ride charge.
     *
     * @param card the payment method to refund
     * @param rideCost the total ride cost in USD
     * @return {@code true} if the refund is valid and processed; {@code false} otherwise
     */
    public boolean processRefund(PaymentMethod card, double rideCost) {
        return card != null && rideCost > 0;
    }

    /**
     * Retrieves the first saved payment method, if any.
     *
     * @return the first {@link PaymentMethod}, or {@code null} if none exist
     */
    public PaymentMethod getFirstPaymentMethod() {
        return methods.isEmpty() ? null : methods.get(0);
    }

    /**
     * Validates the given expiration date to ensure it is properly formatted
     * and not expired.
     *
     * @param exp the expiration date formatted as {@code MM/yy}
     * @return {@code true} if the expiration date is valid and not expired
     */
    private boolean isValidExpiration(String exp) {
        try {
            YearMonth ym = YearMonth.parse(exp, DateTimeFormatter.ofPattern("MM/yy"));
            return ym.isAfter(YearMonth.now().minusMonths(1));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
