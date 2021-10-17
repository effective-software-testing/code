package ch9.large;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryPriceTest {

    @ParameterizedTest
    @CsvSource({"0,0",
            "1,5",
            "3,5",
            "4,12.5",
            "10,12.5",
            "11,20"})
    void deliveryIsAccordingToTheNumberOfItems(int noOfItems, double expectedDeliveryPrice) {
        ShoppingCart cart = new ShoppingCart();
        for(int i = 0; i < noOfItems; i++) {
            cart.add(new Item(ItemType.OTHER, "ANY", 1, 1));
        }

        double price = new DeliveryPrice().priceToAggregate(cart);
        assertThat(price).isEqualTo(expectedDeliveryPrice);
    }
}
