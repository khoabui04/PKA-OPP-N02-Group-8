package Practice6;

import java.util.Date;
import library.BorrowingSlip;

public class DueDateChecker {
    public static boolean isNearDueDate(BorrowingSlip slip) {
        long millisLeft = slip.getDueDate().getTime() - new Date().getTime();
        long daysLeft = millisLeft / (1000 * 60 * 60 * 24);
        return daysLeft <= 3 && daysLeft >= 0;
    }
}