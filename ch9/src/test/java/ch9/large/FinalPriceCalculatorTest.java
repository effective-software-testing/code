package ch9.large;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinalPriceCalculatorTest {

    @Test
    void callAllPriceRules() {
        PriceRule rule1 = mock(PriceRule.class);
        PriceRule rule2 = mock(PriceRule.class);
        PriceRule rule3 = mock(PriceRule.class);

        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item(ItemType.OTHER, "ITEM", 1, 1));

        when(rule1.priceToAggregate(cart)).thenReturn(1.0);
        when(rule2.priceToAggregate(cart)).thenReturn(0.0);
        when(rule3.priceToAggregate(cart)).thenReturn(2.0);

        List<PriceRule> rules = Arrays.asList(rule1, rule2, rule3);
        FinalPriceCalculator calculator = new FinalPriceCalculator(rules);
        double price = calculator.calculate(cart);

        assertThat(price).isEqualTo(3);

    }
}
