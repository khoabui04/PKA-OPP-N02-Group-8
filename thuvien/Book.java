public class Book {
    private String bookID;
    private String bookName;
    private String author;

    // Constructor
    public Book(String bookID, String bookName, String author) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
    }

    // Getters
    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }
}
 