package ch2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items = new ArrayList<CartItem>();

    public void add(CartItem item) {
        this.items.add(item);
    }

    public double totalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
