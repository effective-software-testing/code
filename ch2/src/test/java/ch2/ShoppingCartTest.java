package ch2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {
    private final ShoppingCart cart = new ShoppingCart();

    @Test
    void noItems() {
        assertThat(cart.totalPrice()).isEqualTo(0);
    }

    @Test
    void itemsInTheCart() {
        cart.add(new CartItem("TV", 1, 120));
        assertThat(cart.totalPrice()).isEqualTo(120);

        cart.add(new CartItem("Chocolate", 2, 2.5));
        assertThat(cart.totalPrice()).isEqualTo(120 + 2.5*2);
    }
}
