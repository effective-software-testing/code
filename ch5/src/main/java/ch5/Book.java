package ch5;

public class Book {

    private final String title;
    private final String author;
    private final int qtyOfPages;

    public Book(String title, String author, int qtyOfPages) {
        this.title = title;
        this.author = author;
        this.qtyOfPages = qtyOfPages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQtyOfPages() {
        return qtyOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", qtyOfPages=" + qtyOfPages +
                '}';
    }
}
