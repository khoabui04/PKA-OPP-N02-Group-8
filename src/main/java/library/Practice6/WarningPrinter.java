package Practice6;

import java.util.List;

import library.BorrowingSlip;

/**
 * Lớp in cảnh báo sách gần đến hạn trả cho bạn đọc.
 */
public class WarningPrinter {
    /**
     * In ra danh sách sách gần đến hạn trả cho bạn đọc.
     * @param slips Danh sách phiếu mượn
     * @param borrowerID Mã bạn đọc
     */
    public static void printNearDueBooks(List<BorrowingSlip> slips, String borrowerID) {
        List<BorrowingSlip> userSlips = BorrowingSlipService.getSlipsByBorrowerID(slips, borrowerID);
        boolean found = false;
        for (BorrowingSlip slip : userSlips) {
            if (DueDateChecker.isNearDueDate(slip)) {
                System.out.println("Sách: " + slip.getBook().getBookName() +
                    " | Hạn trả: " + slip.getDueDate());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có sách nào gần đến hạn trả.");
        }
    }
}