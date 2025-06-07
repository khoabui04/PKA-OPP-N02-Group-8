package library.Practice6;

import java.util.ArrayList;
import java.util.List;
import library.BorrowingSlip;

/**
 * Lớp cung cấp dịch vụ xử lý phiếu mượn.
 */
public class BorrowingSlipService {
    /**
     * Lấy danh sách phiếu mượn theo mã bạn đọc.
     * @param slips Danh sách tất cả phiếu mượn
     * @param borrowerID Mã bạn đọc
     * @return Danh sách phiếu mượn của bạn đọc
     */
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

