package ports;

import domain.ShoppingCart;

public interface CustomerNotifier {
    void sendEstimatedDeliveryNotification(ShoppingCart cart);
}
