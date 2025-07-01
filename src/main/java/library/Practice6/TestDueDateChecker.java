package Practice6;

import java.util.Date;
import library.BorrowingSlip;
import library.Borrower;
import library.Book;

public class TestDueDateChecker {
    public static void main(String[] args) {
        try {
            Borrower b = new Borrower("BD001", "Nguyen Van A", "0123456789");
            Book book = new Book("B001", "Lập trình Java", "Nguyen Van Anh");
            Date now = new Date();
            Date dueSoon = new Date(now.getTime() + 2L * 24 * 60 * 60 * 1000); // còn 2 ngày
            Date dueLate = new Date(now.getTime() + 7L * 24 * 60 * 60 * 1000); // còn 7 ngày

            BorrowingSlip slipSoon = new BorrowingSlip("S001", b, book, now, dueSoon);
            BorrowingSlip slipLate = new BorrowingSlip("S002", b, book, now, dueLate);

            System.out.println("Phiếu mượn gần đến hạn trả: " + DueDateChecker.isNearDueDate(slipSoon)); // true
            System.out.println("Phiếu mượn còn xa hạn trả: " + DueDateChecker.isNearDueDate(slipLate)); // false
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc kiểm thử DueDateChecker.");
        }
    }
}