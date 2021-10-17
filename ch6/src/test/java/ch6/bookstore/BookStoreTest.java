package ch6.bookstore;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;

public class BookStoreTest {

    @Test
    void emptyOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BookStore bookStore = new BookStore(bookRepo, process);

        Map<String, Integer> orderMap = new HashMap<>();
        Overview overview = bookStore.getPriceForCart(orderMap);

        assertThat(overview.getTotalPrice()).isEqualTo(0);
        assertThat(overview.getUnavailable()).isEmpty();
    }

    @Test
    void nullOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BookStore bookStore = new BookStore(bookRepo, process);

        Overview overview = bookStore.getPriceForCart(null);

        assertThat(overview).isNull();
    }

    @Test
    void moreComplexOrder() {
        BookRepository bookRepo = mock(BookRepository.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Map<String, Integer> orderMap = new HashMap<>();

        /**
         * Let's have three books:
         * - one where there's enough quantity
         * - one where the available quantity is precisely what's asked in the order
         * - one where there's not enough quantity
         */
        orderMap.put("PRODUCT-ENOUGH-QTY", 5);
        orderMap.put("PRODUCT-PRECISE-QTY", 10);
        orderMap.put("PRODUCT-NOT-ENOUGH", 22);

        Book book1 = new Book("PRODUCT-ENOUGH-QTY", 20, 11); // 11 is more than 5
        when(bookRepo.findByISBN("PRODUCT-ENOUGH-QTY")).thenReturn(book1);
        Book book2 = new Book("PRODUCT-PRECISE-QTY", 25, 10); // 10 == 10
        when(bookRepo.findByISBN("PRODUCT-PRECISE-QTY")).thenReturn(book2);
        Book book3 = new Book("PRODUCT-NOT-ENOUGH", 37, 21);  // 21 < 22
        when(bookRepo.findByISBN("PRODUCT-NOT-ENOUGH")).thenReturn(book3);

        BookStore bookStore = new BookStore(bookRepo, process);
        Overview overview = bookStore.getPriceForCart(orderMap);

        // First, we ensure that the total price is correct
        int expectedPrice =
                5*20 + // from the first product
                        10*25 + // from the second product
                        21*37; // from the third product

        assertThat(overview.getTotalPrice()).isEqualTo(expectedPrice);

        // Then, we ensure that the buy process was called
        verify(process).buyBook(book1, 5);
        verify(process).buyBook(book2, 10);
        verify(process).buyBook(book3, 21);

        // Finally, we ensure that the list of unavailable contains the one book that's missing
        assertThat(overview.getUnavailable())
                .containsExactly(entry(book3, 1));
    }

}
