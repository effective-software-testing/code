package ch2;

import java.util.Objects;

public class CartItem {

    private final String product;
    private final int quantity;
    private final double unitPrice;

    public CartItem(String product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Double.compare(cartItem.unitPrice, unitPrice) == 0 && product.equals(cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, unitPrice);
    }
}
