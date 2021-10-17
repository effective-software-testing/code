package ch9.large;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceOfItemsTest {

    @Test
    void sumOfItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item(ItemType.ELECTRONIC, "PS5", 1, 299));
        cart.add(new Item(ItemType.OTHER, "GOD OF WAR (PS5)", 1, 59));
        cart.add(new Item(ItemType.OTHER, "BOOK", 1, 25));
        cart.add(new Item(ItemType.OTHER, "CD", 2, 12));

        double price = new PriceOfItems().priceToAggregate(cart);

        assertThat(price).isEqualTo(
            1*299 + // PS5
            1*59+ // game
            1*25+ // book
            2*12 // CD
        );
    }

    @Test
    void noItems() {
        ShoppingCart cart = new ShoppingCart();
        double price = new PriceOfItems().priceToAggregate(cart);
        assertThat(price).isEqualTo(0);
    }
}
