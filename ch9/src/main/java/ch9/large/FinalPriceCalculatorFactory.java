package ch9.large;

import java.util.Arrays;
import java.util.List;

public class FinalPriceCalculatorFactory {

    public FinalPriceCalculator build() {
        List<PriceRule> priceRules = Arrays.asList(
                new PriceOfItems(),
                new ExtraChargeForElectronics(),
                new DeliveryPrice());

        return new FinalPriceCalculator(priceRules);
    }
}
