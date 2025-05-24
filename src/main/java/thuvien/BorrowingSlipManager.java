package thuvien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BorrowingSlipManager {
    private static final List<BorrowingSlip> slips = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PHIẾU MƯỢN (CRUD) ---");
            System.out.println("1. Thêm phiếu mượn");
            System.out.println("2. Xem danh sách phiếu mượn");
            System.out.println("3. Sửa phiếu mượn");
            System.out.println("4. Xoá phiếu mượn");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon = Integer.parseInt(scanner.nextLine());

            switch (chon) {
                case 1 -> createSlip();
                case 2 -> readSlips();
                case 3 -> updateSlip();
                case 4 -> deleteSlip();
                case 5 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void createSlip() throws ParseException {
        System.out.print("Nhập mã phiếu: ");
        String slipID = scanner.nextLine();

        System.out.print("Nhập tên người mượn: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mã người mượn: ");
        String borrowerID = scanner.nextLine();

        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        System.out.print("Nhập mã sách: ");
        String bookID = scanner.nextLine();

        System.out.print("Nhập ngày mượn (dd/MM/yyyy): ");
        Date borrowDate = sdf.parse(scanner.nextLine());
        System.out.print("Nhập hạn trả (dd/MM/yyyy): ");
        Date dueDate = sdf.parse(scanner.nextLine());

        Borrower borrower = new Borrower(borrowerID, name);
        Book book = new Book(bookID, title);
        BorrowingSlip slip = new BorrowingSlip(slipID, borrower, book, borrowDate, dueDate);
        slips.add(slip);
        System.out.println("✅ Đã thêm phiếu mượn.");
    }

    private static void readSlips() {
        if (slips.isEmpty()) {
            System.out.println("⚠️ Danh sách phiếu mượn trống.");
        } else {
            System.out.println("📄 Danh sách phiếu mượn:");
            for (BorrowingSlip slip : slips) {
                System.out.println(slip);
            }
        }
    }

    private static void updateSlip() throws ParseException {
        System.out.print("Nhập mã phiếu cần sửa: ");
        String slipID = scanner.nextLine();

        for (int i = 0; i < slips.size(); i++) {
            if (slips.get(i).getSlipID().equals(slipID)) {
                System.out.print("Nhập tên mới người mượn: ");
                String newName = scanner.nextLine();
                System.out.print("Nhập mã mới người mượn: ");
                String newBorrowerID = scanner.nextLine();

                System.out.print("Nhập tên mới sách: ");
                String newTitle = scanner.nextLine();
                System.out.print("Nhập mã mới sách: ");
                String newBookID = scanner.nextLine();

                System.out.print("Nhập ngày mượn mới (dd/MM/yyyy): ");
                Date newBorrowDate = sdf.parse(scanner.nextLine());
                System.out.print("Nhập hạn trả mới (dd/MM/yyyy): ");
                Date newDueDate = sdf.parse(scanner.nextLine());

                Borrower borrower = new Borrower(newBorrowerID, newName);
                Book book = new Book(newBookID, newTitle);
                BorrowingSlip newSlip = new BorrowingSlip(slipID, borrower, book, newBorrowDate, newDueDate);

                slips.set(i, newSlip);
                System.out.println("✅ Đã cập nhật phiếu mượn.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã phiếu.");
    }

    private static void deleteSlip() {
        System.out.print("Nhập mã phiếu cần xoá: ");
        String slipID = scanner.nextLine();
        Iterator<BorrowingSlip> iterator = slips.iterator();
        while (iterator.hasNext()) {
            BorrowingSlip slip = iterator.next();
            if (slip.getSlipID().equals(slipID)) {
                iterator.remove();
                System.out.println("✅ Đã xoá phiếu mượn.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã phiếu.");
    }
}
