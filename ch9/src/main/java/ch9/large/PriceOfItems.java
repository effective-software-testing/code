package ch9.large;

import java.util.List;

public class PriceOfItems implements PriceRule {
    @Override
    public double priceToAggregate(ShoppingCart cart) {

        double price = 0;
        List<Item> items = cart.getItems();
        for (Item item : items) {
            price += item.getPricePerUnit() * item.getQuantity();
        }

        return price;
    }
}
