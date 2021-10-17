package ch9.large;

import java.util.List;

public class FinalPriceCalculator {

    private final List<PriceRule> rules;

    public FinalPriceCalculator(List<PriceRule> rules) {
        this.rules = rules;
    }

    public double calculate(ShoppingCart cart) {
        double finalPrice = 0;

        for (PriceRule rule : rules) {
            finalPrice += rule.priceToAggregate(cart);
        }

        return finalPrice;
    }
}
