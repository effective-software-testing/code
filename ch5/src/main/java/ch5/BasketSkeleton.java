package ch5;

import java.util.HashMap;
import java.util.Map;

public class BasketSkeleton {
    private double totalValue = 0.0;
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) {
        assert product != null : "Product is required";
        assert qtyToAdd > 0 : "Quantity has to be greater than zero";
        double oldTotalValue = totalValue;

        // add the product in the basket
        // update the total value

        assert basket.containsKey(product) : "Product was not inserted in the basket";
        assert totalValue > oldTotalValue : "Total value should be greater than previous total value";
        assert invariant() : "Invariant does not hold";
    }

    public void remove(Product product) {
        assert product != null : "product can't be null";
        assert basket.containsKey(product) : "Product must already be in the basket";

        // remove the product from the basket
        // update the total value

        assert !basket.containsKey(product) : "Product is still in the basket";
        assert invariant() : "Invariant does not hold";
    }

    private boolean invariant() {
        return totalValue >= 0;
    }
}
