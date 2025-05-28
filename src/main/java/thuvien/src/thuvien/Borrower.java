package thuvien;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private String borrowerID;
    private String borrowerName;
    private String phoneNumber;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Borrower(String borrowerID, String borrowerName, String phoneNumber) {
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.phoneNumber = phoneNumber;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
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
        return "Mã: " + borrowerID + ", Tên: " + borrowerName + ", SĐT: " + phoneNumber;
    }
}
