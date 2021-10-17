package ch6.bookstore;

import java.util.Objects;

public class Book {
    private String ISBN;
    private int price;
    private int amount;

    public Book(String ISBN, int price, int amount) {
        this.ISBN = ISBN;
        this.price = price;
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }
}