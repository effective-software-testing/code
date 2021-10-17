package ch6.bookstore;

import java.util.Map;

class BookStore {

    private BookRepository bookRepository;
    private BuyBookProcess process;

    public BookStore(BookRepository bookRepository, BuyBookProcess process) {
        this.bookRepository = bookRepository;
        this.process = process;
    }

    private void retrieveBook(String ISBN, int amount, Overview overview) {
        Book book = bookRepository.findByISBN(ISBN);
        if (book.getAmount() < amount) {
            overview.addUnavailable(book, amount - book.getAmount());
            amount = book.getAmount();
        }

        overview.addToTotalPrice(amount * book.getPrice());
        process.buyBook(book, amount);
    }

    public Overview getPriceForCart(Map<String, Integer> order) {
        if(order==null)
            return null;

        Overview overview = new Overview();
        for (String ISBN : order.keySet())
            retrieveBook(ISBN, order.get(ISBN), overview);
        return overview;
    }
}