package ch6.bookstore;

public interface BookRepository {
    Book findByISBN(String ISBN);
}