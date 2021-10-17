package adapters;

import domain.ShoppingCart;
import ports.DeliveryCenter;

import java.time.LocalDate;
import java.util.Calendar;

public class DeliveryCenterRestApi implements DeliveryCenter {
    @Override
    public LocalDate deliver(ShoppingCart cart) {
        // all the code required to communicate
        // with the delivery API
        // and returns a LocalDate
        return null;
    }
}
