package Practice6;

import java.util.ArrayList;
import java.util.List;
import library.BorrowingSlip;

public class BorrowingSlipService {
    public static List<BorrowingSlip> getSlipsByBorrowerID(List<BorrowingSlip> slips, String borrowerID) {
        List<BorrowingSlip> result = new ArrayList<>();
        for (BorrowingSlip slip : slips) {
            if (slip.getBorrower().getBorrowerID().equals(borrowerID)) {
                result.add(slip);
            }
        }
        return result;
    }
}