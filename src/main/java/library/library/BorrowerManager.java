package library;

import java.util.Scanner;

public class BorrowerManager {
    private static final CrudManager<Borrower> crud = new CrudManager<>("borrowers.dat");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ NGƯỜI MƯỢN ------");
            System.out.println("1. Thêm người mượn");
            System.out.println("2. Xem danh sách người mượn");
            System.out.println("3. Xoá người mượn");
            System.out.println("4. Lưu file");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập mã người mượn: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập tên người mượn: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = scanner.nextLine();
                    crud.add(new Borrower(id, name, phone));
                    break;
                case 2:
                    int i = 0;
                    for (Borrower b : crud.getAll()) {
                        System.out.println((i++) + ": " + b);
                    }
                    break;
                case 3:
                    System.out.print("Nhập vị trí người mượn cần xoá: ");
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