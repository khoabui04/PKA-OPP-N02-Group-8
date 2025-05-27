package thuvien;

import java.util.Date;

public class QuanLi {
    public static void main(String[] args) {
        System.out.println("Hello, World");

        // Tạo đối tượng Book
        Book book = new Book("001", "KhoaPug", "Huy");

        // Tạo đối tượng Borrower
        Borrower borrower = new Borrower("B001", "Nguyen Van A");

        // Người mượn sách
        borrower.borrowBook(book);
        System.out.println(borrower.getBorrowerName() + " is borrowing: " + book.getBookName());

        // Tạo phiếu mượn (BorrowingSlip)
        Date borrowDate = new Date(); // Ngày mượn hiện tại
        Date dueDate = new Date(borrowDate.getTime() + 7L * 24 * 60 * 60 * 1000); // 1 tuần sau

        BorrowingSlip slip = new BorrowingSlip("S001", borrower, book, borrowDate, dueDate);

        // In thông tin phiếu mượn
        System.out.println("Borrowing Slip ID: " + slip.getSlipID());
        System.out.println("Book ID: " + slip.getBook().getBookID());
        System.out.println("Book Name: " + slip.getBook().getBookName());
        System.out.println("Author: " + slip.getBook().getAuthor());
        System.out.println("Borrower: " + slip.getBorrower().getBorrowerName());
        System.out.println("Borrow Date: " + slip.getBorrowDate());
        System.out.println("Due Date: " + slip.getDueDate());
    }
}
