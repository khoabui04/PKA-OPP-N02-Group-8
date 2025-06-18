package library;

import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;

public class BorrowingSlipManager {
    private static final CrudManager<BorrowingSlip> crud = new CrudManager<>("slips.dat");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ PHIẾU MƯỢN ------");
            System.out.println("1. Thêm phiếu mượn");
            System.out.println("2. Xem danh sách phiếu mượn");
            System.out.println("3. Xoá phiếu mượn");
            System.out.println("4. Lưu file");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập mã phiếu mượn: ");
                    String slipID = scanner.nextLine();

                        System.out.print("Nhập mã người mượn: ");
                        String borrowerID = scanner.nextLine();
                        System.out.print("Nhập tên người mượn: ");
                        String borrowerName = scanner.nextLine();
                        System.out.print("Nhập số điện thoại: ");
                        String phone = scanner.nextLine();
                        Borrower borrower = new Borrower(borrowerID, borrowerName, phone);

                        System.out.print("Nhập mã sách: ");
                        String bookID = scanner.nextLine();
                        System.out.print("Nhập tên sách: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Nhập tác giả: ");
                        String author = scanner.nextLine();
                        Book book = new Book(bookID, bookName, author);

                        Date borrowDate = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(borrowDate);
                        cal.add(Calendar.DAY_OF_MONTH, 7);
                        Date dueDate = cal.getTime();

                    BorrowingSlip slip = new BorrowingSlip(slipID, borrower, book, borrowDate, dueDate);
                    crud.add(slip);
                    break;
                case 2:
                    int i = 0;
                    for (BorrowingSlip s : crud.getAll()) {
                        System.out.println((i++) + ": " + s);
                    }
                    break;
                case 3:
                    System.out.print("Nhập vị trí phiếu mượn cần xoá: ");
                    int idx = Integer.parseInt(scanner.nextLine());
                    crud.remove(idx);
                    break;
                case 4:
                    crud.save();
                    break;
                case 0:
                    crud.save();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        }
    }
}
