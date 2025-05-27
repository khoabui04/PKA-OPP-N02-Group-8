package thuvien;

import java.util.*;

public class BorrowerManager {
    private static final List<Borrower> borrowerList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ NGƯỜI MƯỢN ------");
            System.out.println("1. Thêm người mượn (CREATE)");
            System.out.println("2. Xem danh sách người mượn (READ)");
            System.out.println("3. Sửa thông tin người mượn (UPDATE)");
            System.out.println("4. Xoá người mượn (DELETE)");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
                continue;
            }

            switch (choice) {
                case 1 -> createBorrower();
                case 2 -> readBorrowers();
                case 3 -> updateBorrower();
                case 4 -> deleteBorrower();
                case 0 -> {
                    System.out.println("Thoát chương trình quản lý người mượn.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // CREATE
    private static void createBorrower() {
        System.out.print("Nhập mã người mượn: ");
        String borrowerID = scanner.nextLine();

        System.out.print("Nhập tên người mượn: ");
        String borrowerName = scanner.nextLine();

        System.out.print("Nhập số điện thoại (có thể bỏ qua): ");
        String phoneNumber = scanner.nextLine();

        Borrower borrower = new Borrower(borrowerID, borrowerName, phoneNumber);
        borrowerList.add(borrower);
        System.out.println("Thêm người mượn thành công.");
    }

    // READ
    private static void readBorrowers() {
        if (borrowerList.isEmpty()) {
            System.out.println("Không có người mượn nào trong danh sách.");
            return;
        }

        System.out.println("\nDANH SÁCH NGƯỜI MƯỢN:");
        for (Borrower borrower : borrowerList) {
            System.out.println(borrower);
        }
    }

    // UPDATE
    private static void updateBorrower() {
        System.out.print("Nhập mã người mượn cần sửa: ");
        String borrowerID = scanner.nextLine();

        for (Borrower borrower : borrowerList) {
            if (borrower.getBorrowerID().equals(borrowerID)) {
                System.out.print("Nhập tên người mượn mới: ");
                String newName = scanner.nextLine();
                borrower.setBorrowerName(newName);

                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = scanner.nextLine();
                borrower.setPhoneNumber(newPhone);

                System.out.println("Cập nhật người mượn thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy người mượn với mã đã nhập.");
    }

    // DELETE
    private static void deleteBorrower() {
        System.out.print("Nhập mã người mượn cần xoá: ");
        String borrowerID = scanner.nextLine();

        Iterator<Borrower> iterator = borrowerList.iterator();
        while (iterator.hasNext()) {
            Borrower borrower = iterator.next();
            if (borrower.getBorrowerID().equals(borrowerID)) {
                iterator.remove();
                System.out.println("Xoá người mượn thành công.");
                return;
            }
        }

        System.out.println("Không tìm thấy người mượn để xoá.");
    }
}
