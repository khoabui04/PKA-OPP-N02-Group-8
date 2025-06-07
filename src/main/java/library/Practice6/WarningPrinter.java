package src;

import java.util.List;

public class WarningPrinter {
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