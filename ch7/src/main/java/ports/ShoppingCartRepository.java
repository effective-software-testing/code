package ports;


import domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepository {
    List<ShoppingCart> cartsPaidToday();
    void persist(ShoppingCart cart);
}
