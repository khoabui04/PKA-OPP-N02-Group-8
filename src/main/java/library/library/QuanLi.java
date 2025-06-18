package library;
import java.util.Date;

public class QuanLi {
    public static void main(String[] args) {
        try {
            Book book = new Book("001", "KhoaPug", "Huy");
            Borrower borrower = new Borrower("B001", "Nguyen Van A", "0123456789");
            borrower.borrowBook(book);
            Date borrowDate = new Date();
            Date dueDate = new Date(borrowDate.getTime() + 7L * 24 * 60 * 60 * 1000);
            BorrowingSlip slip = new BorrowingSlip("S001", borrower, book, borrowDate, dueDate);

            System.out.println("Book: " + book);
            System.out.println("Borrower: " + borrower);
            System.out.println("BorrowingSlip: " + slip);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc kiểm thử QuanLi.");
        }
    }
}