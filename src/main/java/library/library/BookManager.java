package library;

import java.util.Scanner;

public class BookManager {
private static final CrudManager<Book> crud = new CrudManager<>("books.dat");
private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ SÁCH ------");
            System.out.println("1. Thêm sách");
            System.out.println("2. Xem danh sách sách");
            System.out.println("3. Xoá sách");
            System.out.println("4. Lưu file");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập mã sách: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập tên sách: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập tác giả: ");
                    String author = scanner.nextLine();
                    crud.add(new Book(id, name, author));
                    break;
                case 2:
                    int i = 0;
                    for (Book b : crud.getAll()) {
                        System.out.println((i++) + ": " + b);
                    }
                    break;
                case 3:
                    System.out.print("Nhập vị trí sách cần xoá: ");
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