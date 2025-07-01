package library;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookID;
    private String bookName;
    private String author;

    public Book(String bookID, String bookName, String author) {
        try {
            this.bookID = bookID;
            this.bookName = bookName;
            this.author = author;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Khối finally của constructor Book
        }
    }

    public String getBookID() {
        try {
            return bookID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getBookID
        }
    }

    public void setBookID(String bookID) {
        try {
            this.bookID = bookID;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Khối finally của setBookID
        }
    }

    public String getBookName() {
        try {
            return bookName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getBookName
        }
    }

    public void setBookName(String bookName) {
        try {
            this.bookName = bookName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Khối finally của setBookName
        }
    }

    public String getAuthor() {
        try {
            return author;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getAuthor
        }
    }

    public void setAuthor(String author) {
        try {
            this.author = author;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Khối finally của setAuthor
        }
    }

    @Override
    public String toString() {
        try {
            return "Book{" +
                    "bookID='" + bookID + '\'' +
                    ", bookName='" + bookName + '\'' +
                    ", author='" + author + '\'' +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            // Khối finally của toString
        }
    }
}