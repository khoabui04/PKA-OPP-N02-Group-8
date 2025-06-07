package Practice6;

import java.util.Date;

import library.BorrowingSlip;

/**
 * Lớp kiểm tra phiếu mượn có gần đến hạn trả hay không.
 */
public class DueDateChecker {
    /**
     * Kiểm tra phiếu mượn còn <= 3 ngày đến hạn trả.
     * @param slip Phiếu mượn cần kiểm tra
     * @return true nếu còn <= 3 ngày đến hạn trả, ngược lại false
     */
    public static boolean isNearDueDate(BorrowingSlip slip) {
        long millisLeft = slip.getDueDate().getTime() - new Date().getTime();
        long daysLeft = millisLeft / (1000 * 60 * 60 * 24);
        return daysLeft <= 3 && daysLeft >= 0;
    }
}