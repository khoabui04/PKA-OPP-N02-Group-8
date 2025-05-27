package thuvien;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Borrower {
    private String borrowerID;
    private String borrowerName;
    private String phoneNumber;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Borrower(String borrowerID, String borrowerName) {
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
    }

    public Borrower(String borrowerID, String borrowerName, String phoneNumber) {
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.phoneNumber = phoneNumber;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerID='" + borrowerID + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                (phoneNumber != null ? ", phoneNumber='" + phoneNumber + '\'' : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrower)) return false;
        Borrower that = (Borrower) o;
        return Objects.equals(borrowerID, that.borrowerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerID);
    }
}
