package Practice6;

import java.util.*;
import library.BorrowingSlip;
import library.Borrower;
import library.Book;

public class TestWarningPrinter {
    public static void main(String[] args) {
        List<BorrowingSlip> slips = new ArrayList<>();

        Borrower b1 = new Borrower("BD001", "Nguyen Van A", "0123456789");
        Book book1 = new Book("B001", "Lập trình Java", "Nguyen Van Anh");
        Book book2 = new Book("B002", "Cấu trúc dữ liệu", "Nguyen Van Binh");
        Date now = new Date();
        Date dueSoon = new Date(now.getTime() + 2L * 24 * 60 * 60 * 1000); // còn 2 ngày
        Date dueLate = new Date(now.getTime() + 7L * 24 * 60 * 60 * 1000); // còn 7 ngày

        slips.add(new BorrowingSlip("S001", b1, book1, now, dueSoon));
        slips.add(new BorrowingSlip("S002", b1, book2, now, dueLate));

        System.out.println("Cảnh báo sách gần đến hạn trả cho BD001:");
        WarningPrinter.printNearDueBooks(slips, "BD001");
    }
}