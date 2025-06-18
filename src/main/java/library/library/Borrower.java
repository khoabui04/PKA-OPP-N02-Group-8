package library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Borrower implements Serializable {
    private String borrowerID;
    private String borrowerName;
    private String phoneNumber;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Borrower(String borrowerID, String borrowerName, String phoneNumber) {
        try {
            this.borrowerID = borrowerID;
            this.borrowerName = borrowerName;
            this.phoneNumber = phoneNumber;
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo đối tượng Borrower: " + e.getMessage());
        }
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        try {
            if (borrowerName == null || borrowerName.isEmpty()) {
                throw new IllegalArgumentException("Tên người mượn không được để trống.");
            }
            this.borrowerName = borrowerName;
        } catch (Exception e) {
            System.out.println("Lỗi khi đặt tên người mượn: " + e.getMessage());
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Số điện thoại không được để trống.");
            }
            this.phoneNumber = phoneNumber;
        } catch (Exception e) {
            System.out.println("Lỗi khi đặt số điện thoại: " + e.getMessage());
        }
    }

    public void borrowBook(Book book) {
        try {
            if (book == null) {
                throw new IllegalArgumentException("Sách không hợp lệ.");
            }
            borrowedBooks.add(book);
        } catch (Exception e) {
            System.out.println("Lỗi khi mượn sách: " + e.getMessage());
        }
    }

    public List<Book> getBorrowedBooks() {
        try {
            return borrowedBooks;
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách sách mượn: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        try {
            return "Borrower{" +
                    "borrowerID='" + borrowerID + '\'' +
                    ", borrowerName='" + borrowerName + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", borrowedBooks=" + borrowedBooks.size() +
                    '}';
        } catch (Exception e) {
            return "Lỗi khi hiển thị Borrower: " + e.getMessage();
        }
    }
}