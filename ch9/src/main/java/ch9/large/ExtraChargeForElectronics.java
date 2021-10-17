package ch9.large;

import java.util.List;

public class ExtraChargeForElectronics implements PriceRule {
    @Override
    public double priceToAggregate(ShoppingCart cart) {

        List<Item> items = cart.getItems();

        boolean hasAnElectronicDevice = items.stream().anyMatch(it -> it.getType() == ItemType.ELECTRONIC);

        if(hasAnElectronicDevice)
            return 7.50;

        return 0;
    }
}
