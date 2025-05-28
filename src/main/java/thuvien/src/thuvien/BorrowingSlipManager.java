package src.thuvien;

import java.util.*;

public class BorrowingSlipManager {
    private static final List<BorrowingSlip> slipList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ PHIẾU MƯỢN ------");
            System.out.println("1. Thêm phiếu mượn (CREATE)");
            System.out.println("2. Xem danh sách phiếu mượn (READ)");
            System.out.println("3. Sửa phiếu mượn (UPDATE)");
            System.out.println("4. Xoá phiếu mượn (DELETE)");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

switch (choice) {
    case 1:
    createSlip();
    break;
    case 2:
    readSlips();
    break;
    case 3:
    updateSlip();
    break;
    case 4:
    deleteSlip();
    break;
    case 0: {
        System.out.println("Thoát chương trình.");
        return;
    }
    default:
        System.out.println("Lựa chọn không hợp lệ.");
}

        }
    }

    // CREATE
    private static void createSlip() {
        System.out.print("Nhập mã phiếu mượn: ");
        String slipID = scanner.nextLine();

        System.out.print("Nhập tên người mượn: ");
        String borrowerName = scanner.nextLine();
        System.out.print("Nhập ID người mượn: ");
        String borrowerID = scanner.nextLine();
        Borrower borrower = new Borrower(borrowerID, borrowerName);

        System.out.print("Nhập mã sách: ");
        String bookID = scanner.nextLine();
        System.out.print("Nhập tên sách: ");
        String bookName = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        String author = scanner.nextLine();
        Book book = new Book(bookID, bookName, author);

        Date borrowDate = new Date(); // lấy ngày hiện tại
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowDate);
        cal.add(Calendar.DAY_OF_MONTH, 7); // hạn trả sau 7 ngày
        Date dueDate = cal.getTime();

        BorrowingSlip slip = new BorrowingSlip(slipID, borrower, book, borrowDate, dueDate);
        slipList.add(slip);
        System.out.println("✅ Thêm phiếu mượn thành công.");
    }

    // READ
    private static void readSlips() {
        if (slipList.isEmpty()) {
            System.out.println("📭 Không có phiếu mượn nào.");
            return;
        }

        System.out.println("\n📋 DANH SÁCH PHIẾU MƯỢN:");
        for (BorrowingSlip slip : slipList) {
            System.out.println(slip);
        }
    }

    // UPDATE
    private static void updateSlip() {
        System.out.print("Nhập mã phiếu mượn cần sửa: ");
        String id = scanner.nextLine();

        for (BorrowingSlip slip : slipList) {
            if (slip.getSlipID().equals(id)) {
                System.out.print("Nhập tên sách mới: ");
                String newBookName = scanner.nextLine();
                slip.getBook().setBookName(newBookName);  // cần setter trong Book
                System.out.println("✅ Cập nhật thành công.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy phiếu mượn.");
    }

    // DELETE
    private static void deleteSlip() {
        System.out.print("Nhập mã phiếu mượn cần xoá: ");
        String id = scanner.nextLine();

        Iterator<BorrowingSlip> iterator = slipList.iterator();
        while (iterator.hasNext()) {
            BorrowingSlip slip = iterator.next();
            if (slip.getSlipID().equals(id)) {
                iterator.remove();
                System.out.println("🗑️ Xoá thành công.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy phiếu mượn.");
    }
}
