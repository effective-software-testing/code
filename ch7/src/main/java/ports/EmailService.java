package ports;

import domain.ShoppingCart;

public interface EmailService {
    void sendEstimatedDeliveryEmail(ShoppingCart cart);
}
