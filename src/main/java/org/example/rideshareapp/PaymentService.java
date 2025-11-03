package org.example.rideshareapp;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PaymentService {

    public static class PaymentMethod {
        long cardNumber;
        long cvv;
        String expirationDate;

        public PaymentMethod(long cardNumber, long cvv, String expirationDate) {
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.expirationDate = expirationDate;
        }
    }

    private ArrayList<PaymentMethod> methods = new ArrayList<>();

    public boolean addPaymentMethod(long cardNumber, long cardVerificationValue, String expirationDate) {
        if (String.valueOf(cardNumber).length() != 16) return false;
        if (String.valueOf(cardVerificationValue).length() != 3) return false;
        if (!isValidExpiration(expirationDate)) return false;

        methods.add(new PaymentMethod(cardNumber, cardVerificationValue, expirationDate));
        return true;
    }

    public void removePaymentMethod(PaymentMethod card) {
        methods.remove(card);
    }

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

    public boolean processPayment(PaymentMethod card, double rideCost) {
        return card != null && rideCost > 0;
    }

    public boolean processRefund(PaymentMethod card, double rideCost) {
        return card != null && rideCost > 0;
    }

    public PaymentMethod getFirstPaymentMethod() {
        return methods.isEmpty() ? null : methods.get(0);
    }

    private boolean isValidExpiration(String exp) {
        try {
            YearMonth ym = YearMonth.parse(exp, DateTimeFormatter.ofPattern("MM/yy"));
            return ym.isAfter(YearMonth.now().minusMonths(1));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
