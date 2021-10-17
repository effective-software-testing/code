package domain;

import java.time.LocalDate;
import java.util.Calendar;

public class ShoppingCart {
    private boolean readyForDelivery = false;
    private double value = 0;

    public ShoppingCart(double value) {
        this.value = value;
    }
    // more info about the shopping cart...

    public void markAsReadyForDelivery(LocalDate estimatedDayOfDelivery) {
        this.readyForDelivery = true;
        // ...
    }

    public boolean isReadyForDelivery() {
        return readyForDelivery;
    }

    public double getValue() {
        return this.value;
    }
}
