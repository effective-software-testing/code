package ch5;

import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {
    private Basket basket = new Basket();

    @Test
    void addProducts() {

        basket.add(new Product("TV", valueOf(10)), 2);
        basket.add(new Product("Playstation", valueOf(100)), 1);

        assertThat(basket.getTotalValue())
                .isEqualByComparingTo(valueOf(10*2 + 100*1));
    }

    @Test
    void addSameProductTwice() {

        Product p = new Product("TV", valueOf(10));
        basket.add(p, 2);
        basket.add(p, 3);

        assertThat(basket.getTotalValue())
                .isEqualTo(valueOf(10*5));
    }

    @Test
    void removeProducts() {

        basket.add(new Product("TV", valueOf(100)), 1);

        Product p = new Product("Playstation", valueOf(10));
        basket.add(p, 2);
        basket.remove(p);

        assertThat(basket.getTotalValue())
                .isEqualTo(valueOf(100));
    }

    // tests for exceptional cases...

}
