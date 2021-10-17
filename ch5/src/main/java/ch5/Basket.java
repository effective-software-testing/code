package ch5;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.math.BigDecimal.valueOf;

public class Basket {
    private BigDecimal totalValue = BigDecimal.ZERO;
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) {
        assert product != null : "Product is required";
        assert qtyToAdd > 0 : "Quantity has to be greater than zero";
        BigDecimal oldTotalValue = totalValue;

        int existingQuantity = basket.getOrDefault(product, 0);
        int newQuantity = existingQuantity + qtyToAdd;
        basket.put(product, newQuantity);

        BigDecimal valueAlreadyInTheCart = product.getPrice().multiply(valueOf(existingQuantity));
        BigDecimal newFinalValueForTheProduct = product.getPrice().multiply(valueOf(newQuantity));

        totalValue = totalValue
                .subtract(valueAlreadyInTheCart)
                .add(newFinalValueForTheProduct);

        assert basket.containsKey(product) : "Product was not inserted in the basket";
        assert totalValue.compareTo(oldTotalValue) == 1 : "Total value should be greater than previous total value";
        assert invariant() : "Invariant does not hold";
    }

    public void remove(Product product) {
        assert product != null : "product can't be null";
        assert basket.containsKey(product) : "Product must already be in the basket";

        int qty = basket.get(product);

        totalValue = totalValue.subtract(product.getPrice().multiply(valueOf(qty)));

        basket.remove(product);

        assert !basket.containsKey(product) : "Product is still in the basket";
        assert invariant() : "Invariant does not hold";
    }

    private boolean invariant() {
        return totalValue.compareTo(BigDecimal.ZERO) >= 0;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public int quantityOf(Product product) {
        assert basket.containsKey(product);
        return basket.get(product);
    }

    public Set<Product> products() {
        return Collections.unmodifiableSet(basket.keySet());
    }

    @Override
    public String toString() {
        return "BasketCase{" +
                "totalValue=" + totalValue +
                ", basket=" + basket +
                '}';
    }
}
