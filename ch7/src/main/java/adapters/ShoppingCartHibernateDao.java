package adapters;


import domain.ShoppingCart;
import ports.ShoppingCartRepository;

import java.util.List;

public class ShoppingCartHibernateDao implements ShoppingCartRepository {
    @Override
    public List<ShoppingCart> cartsPaidToday() {
        // a hibernate query to get the list of all
        // invoices that were paid today
        return null;
    }

    @Override
    public void persist(ShoppingCart cart) {
        // a hibernate code to persist the cart
        // in the database
    }
}
