package ch9.large;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Electronics:
 * - Has an item in the cart
 * - No items in the cart
 *
 * Delivery:
 * - 1 to 3 items
 * - 4 to 10 items
 * - More than 10 items
 *
 * Items:
 * - Empty
 * - 1 single element
 * - Many elements
 */
public class FinalPriceCalculatorLargerTest {

    private final FinalPriceCalculator calculator = new FinalPriceCalculatorFactory().build();

    @Test
    void appliesAllRules() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item(ItemType.ELECTRONIC, "PS5", 1, 299));
        cart.add(new Item(ItemType.OTHER, "BOOK", 1, 29));
        cart.add(new Item(ItemType.OTHER, "CD", 2, 12));
        cart.add(new Item(ItemType.OTHER, "CHOCOLATE", 3, 1.50));

        double price = calculator.calculate(cart);

        double expectedPrice =
                299 + 29 + 12 * 2 + 1.50 * 3 + // price of the items
                7.50 + // has an electronic
                12.5; // delivery price

        assertThat(price).isEqualTo(expectedPrice);
    }
}
