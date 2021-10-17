package ports;

import domain.ShoppingCart;

public interface SAP {
    void cartReadyForDelivery(ShoppingCart cart);
}
