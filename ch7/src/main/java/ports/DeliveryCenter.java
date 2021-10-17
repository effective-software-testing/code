package ports;

import domain.ShoppingCart;

import java.time.LocalDate;
import java.util.Calendar;

public interface DeliveryCenter {
    LocalDate deliver(ShoppingCart cart);
}
