package thuvien;

import java.util.Date;

public class TestBorrowingSlip {
    public static void main(String[] args) {
        Book book = new Book("001", "KhoaPug", "Huy");
        Borrower borrower = new Borrower("B001", "Nguyen Van A");
        borrower.borrowBook(book);

        Date borrowDate = new Date();
        Date dueDate = new Date(borrowDate.getTime() + 7L * 24 * 60 * 60 * 1000);

        BorrowingSlip slip = new BorrowingSlip("S001", borrower, book, borrowDate, dueDate);

        System.out.println("Slip ID: " + slip.getSlipID());
        System.out.println("Book: " + slip.getBook().getBookName());
        System.out.println("Borrower: " + slip.getBorrower().getBorrowerName());
        System.out.println("Borrow Date: " + slip.getBorrowDate());
        System.out.println("Due Date: " + slip.getDueDate());
    }
}
