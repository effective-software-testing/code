package ch9.large;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {

    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int numberOfItems() {
        return items.size();
    }
}
