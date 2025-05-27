package thuvien;

import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- MENU QUẢN LÝ SÁCH ---");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị danh sách sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Xoá sách");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createBook();
                    break;
                case 2:
                    readBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // C - Create
    private static void createBook() {
        System.out.print("Nhập mã sách: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên sách: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, name, author);
        books.add(newBook);
        System.out.println("✅ Thêm sách thành công!");
    }

    // R - Read
    private static void readBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 Không có sách nào trong danh sách.");
        } else {
            System.out.println("--- DANH SÁCH SÁCH ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // U - Update
    private static void updateBook() {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String id = scanner.nextLine();

        for (Book book : books) {
            if (book.getBookID().equals(id)) {
                System.out.print("Nhập tên sách mới: ");
                String newName = scanner.nextLine();
                System.out.print("Nhập tác giả mới: ");
                String newAuthor = scanner.nextLine();

                book.setBookName(newName);
                book.setAuthor(newAuthor);
                System.out.println("✅ Cập nhật thành công!");
                return;
            }
        }

        System.out.println("❌ Không tìm thấy sách với mã " + id);
    }

    // D - Delete
    private static void deleteBook() {
        System.out.print("Nhập mã sách cần xoá: ");
        String id = scanner.nextLine();

        Book toRemove = null;
        for (Book book : books) {
            if (book.getBookID().equals(id)) {
                toRemove = book;
                break;
            }
        }

        if (toRemove != null) {
            books.remove(toRemove);
            System.out.println("✅ Đã xoá sách.");
        } else {
            System.out.println("❌ Không tìm thấy sách với mã " + id);
        }
    }
}

