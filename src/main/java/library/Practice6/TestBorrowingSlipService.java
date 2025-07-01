package Practice6;

import java.util.*;
import library.BorrowingSlip;
import library.Borrower;
import library.Book;

public class TestBorrowingSlipService {
    public static void main(String[] args) {
        try {
            List<BorrowingSlip> slips = new ArrayList<>();

            Borrower b1 = new Borrower("BD001", "Nguyen Van A", "0123456789");
            Borrower b2 = new Borrower("BD002", "Tran Thi B", "0987654321");
            Book book1 = new Book("B001", "Lập trình Java", "Nguyen Van Anh");
            Book book2 = new Book("B002", "Cấu trúc dữ liệu", "Nguyen Van Binh");
            Date now = new Date();
            Date due = new Date(now.getTime() + 2L * 24 * 60 * 60 * 1000); // còn 2 ngày
            Date due2 = new Date(now.getTime() + 5L * 24 * 60 * 60 * 1000); // còn 5 ngày

            slips.add(new BorrowingSlip("S001", b1, book1, now, due));
            slips.add(new BorrowingSlip("S002", b1, book2, now, due2));
            slips.add(new BorrowingSlip("S003", b2, book1, now, due));

            String borrowerID = "BD001";
            List<BorrowingSlip> result = BorrowingSlipService.getSlipsByBorrowerID(slips, borrowerID);
            System.out.println("Số phiếu mượn của " + borrowerID + ": " + result.size());
            for (BorrowingSlip slip : result) {
                System.out.println("- " + slip.getBook().getBookName() + " | Hạn trả: " + slip.getDueDate());
            }

            System.out.println("\nCảnh báo sách gần đến hạn trả:");
            WarningPrinter.printNearDueBooks(slips, borrowerID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc kiểm thử BorrowingSlipService.");
        }
    }
}