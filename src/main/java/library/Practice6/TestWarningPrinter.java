package Practice6;

import java.util.*;
import library.BorrowingSlip;
import library.Borrower;
import library.Book;

/**
 * Lớp kiểm thử chức năng cảnh báo sách gần đến hạn trả.
 */
public class TestWarningPrinter {
    public static void main(String[] args) {
        // Tạo dữ liệu mẫu
        Borrower b = new Borrower("B001", "Nguyen Van A", "0123456789");
        Book bk = new Book("001", "KhoaPug", "Huy");
        Date borrowDate = new Date();
        Date dueDate = new Date(borrowDate.getTime() + 2L * 24 * 60 * 60 * 1000); // 2 ngày nữa
        BorrowingSlip slip = new BorrowingSlip("S001", b, bk, borrowDate, dueDate);

        List<BorrowingSlip> slips = new ArrayList<>();
        slips.add(slip);

        // Gọi hàm cảnh báo
        WarningPrinter.printNearDueBooks(slips, "B001");
    }
}