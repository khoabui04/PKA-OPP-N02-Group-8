package library;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookID;
    private String bookName;
    private String author;

    public Book(String bookID, String bookName, String author) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
    }

    public String getBookID() { return bookID; }
    public void setBookID(String bookID) { this.bookID = bookID; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}