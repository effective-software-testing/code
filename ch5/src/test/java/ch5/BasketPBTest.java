package ch5;

import net.jqwik.api.*;
import net.jqwik.api.stateful.Action;
import net.jqwik.api.stateful.ActionSequence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketPBTest {

    class AddAction implements Action<Basket> {

        private final Product product;
        private final int qty;

        public AddAction(Product product, int qty) {
            this.product = product;
            this.qty = qty;
        }
        @Override
        public Basket run(Basket basket) {
            BigDecimal currentValue = basket.getTotalValue();
            basket.add(product, qty);

            BigDecimal newProductValue = product.getPrice().multiply(valueOf(qty));
            BigDecimal newValue = currentValue.add(newProductValue);
            assertThat(basket.getTotalValue()).isEqualByComparingTo(newValue);
            return basket;
        }

        @Override
        public String toString() {
            return "AddAction{" +
                    "product=" + product +
                    ", qty=" + qty +
                    '}';
        }
    }

    class RemoveAction implements Action<Basket> {

        @Override
        public Basket run(Basket basket) {
            BigDecimal currentValue = basket.getTotalValue();

            Set<Product> productsInBasket = basket.products();

            // if the basket is empty, simply skip this action
            if(productsInBasket.isEmpty())
                return basket;

            // pick a random element in the basket to be removed
            Product randomProduct = pickRandom(productsInBasket);
            double currentProductQty = basket.quantityOf(randomProduct);

            basket.remove(randomProduct);

            BigDecimal basketValueWithoutRandomProduct = currentValue
                    .subtract(randomProduct.getPrice().multiply(valueOf(currentProductQty)));

            assertThat(basket.getTotalValue())
                    .isEqualByComparingTo(basketValueWithoutRandomProduct);

            return basket;
        }

        private Product pickRandom(Set<Product> set){

            Random random = new Random();
            int randomNumber = random.nextInt(set.size());

            int currentIndex = 0;
            Product randomElement = null;

            for(Product element : set){
                randomElement = element;

                if(currentIndex == randomNumber)
                    return randomElement;

                currentIndex++;
            }

            return randomElement;
        }

        @Override
        public String toString() {
            return "RemoveAction";
        }
    }

    static List<Product> randomProducts = new ArrayList<>() {{
        add(new Product("TV", new BigDecimal("100")));
        add(new Product("Playstation", new BigDecimal("150.3")));
        add(new Product("Refrigerator", new BigDecimal("180.27")));
        add(new Product("Soda", new BigDecimal("2.69")));
    }};

    private Arbitrary<AddAction> addAction() {
        // create an arbitrary product out of the list of pre-defined products
        Arbitrary<Product> products = Arbitraries.oneOf(
            randomProducts
                .stream()
                .map(product -> Arbitraries.of(product))
                .collect(Collectors.toList()));

        // create arbitrary quantities
        Arbitrary<Integer> qtys = Arbitraries.integers().between(1, 100);

        // now, we combine products and qtys and generate 'add actions'
        return Combinators
                .combine(products, qtys)
                .as((product, qty) -> new AddAction(product, qty));
    }

    @Property(afterFailure = AfterFailureMode.SAMPLE_ONLY)
    void sequenceOfAddsAndRemoves(@ForAll("addsAndRemoves") ActionSequence<Basket> actions) {
        actions.run(new Basket());
    }

    @Provide
    Arbitrary<ActionSequence<Basket>> addsAndRemoves() {
        // generate arbitrary sequences of adds and removes
        return Arbitraries.sequences(Arbitraries.oneOf(
                addAction(),
                removeAction()));
    }

    private Arbitrary<RemoveAction> removeAction() {
        return Arbitraries.of(new RemoveAction());
    }


}
