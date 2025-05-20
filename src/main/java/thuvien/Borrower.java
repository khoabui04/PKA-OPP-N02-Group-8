package thuvien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Borrower {
    private final String borrowerID;
    private final String borrowerName;
    private final List<Book> borrowedBooks;

    public Borrower(String borrowerID, String borrowerName) {
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerID='" + borrowerID + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrower)) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(borrowerID, borrower.borrowerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerID);
    }
}
