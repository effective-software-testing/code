package ch5;

import net.jqwik.api.*;

public class BookTest {

    @Property
    void differentBooks(@ForAll("books") Book book) {
        // different books!
        System.out.println(book);

        // write your test here!

    }

    @Provide
    Arbitrary<Book> books() {
        Arbitrary<String> titles = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(10).ofMaxLength(100);
        Arbitrary<String> authors = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(5).ofMaxLength(21);
        Arbitrary<Integer> qtyOfPages = Arbitraries.integers().between(0, 450);
        return Combinators.combine(titles, authors, qtyOfPages)
                .as((title, author, pages) -> new Book(title, author, pages));
    }
}
