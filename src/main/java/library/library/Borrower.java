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
        } finally {
            // Khối finally của constructor Borrower
        }
    }

    public String getBorrowerID() {
        try {
            return borrowerID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getBorrowerID
        }
    }

    public String getBorrowerName() {
        try {
            return borrowerName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getBorrowerName
        }
    }

    public void setBorrowerName(String borrowerName) {
        try {
            if (borrowerName == null || borrowerName.isEmpty()) {
                throw new IllegalArgumentException("Tên người mượn không được để trống.");
            }
            this.borrowerName = borrowerName;
        } catch (Exception e) {
            System.out.println("Lỗi khi đặt tên người mượn: " + e.getMessage());
        } finally {
            // Khối finally của setBorrowerName
        }
    }

    public String getPhoneNumber() {
        try {
            return phoneNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Khối finally của getPhoneNumber
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Số điện thoại không được để trống.");
            }
            this.phoneNumber = phoneNumber;
        } catch (Exception e) {
            System.out.println("Lỗi khi đặt số điện thoại: " + e.getMessage());
        } finally {
            // Khối finally của setPhoneNumber
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
        } finally {
            // Khối finally của borrowBook
        }
    }

    public List<Book> getBorrowedBooks() {
        try {
            return borrowedBooks;
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách sách mượn: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            // Khối finally của getBorrowedBooks
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