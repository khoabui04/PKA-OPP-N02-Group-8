package src.thuvien;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class BookManager {
    private static final List<Book> bookList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n------ MENU QUẢN LÝ SÁCH ------");
            System.out.println("1. Thêm sách (CREATE)");
            System.out.println("2. Xem danh sách sách (READ)");
            System.out.println("3. Sửa thông tin sách (UPDATE)");
            System.out.println("4. Xoá sách (DELETE)");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số hợp lệ.");
                continue;
            }

            switch (choice) {
                case 1:
<<<<<<< HEAD
                createBook();
                break;
=======
                 createBook();
                 break;
>>>>>>> bba4ff6c75c190cf0ecfa8a8199c5c4a9141b900
                case 2:
                readBooks();
                break;
                case 3:
                updateBook();
                break;
                case 4:
                deleteBook();
                break;
                case 0:{
                    System.out.println("📚 Thoát chương trình quản lý sách.");
                    return;
                }
<<<<<<< HEAD
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ.");
                    break;
                
=======
                default:System.out.println("❌ Lựa chọn không hợp lệ.");
>>>>>>> bba4ff6c75c190cf0ecfa8a8199c5c4a9141b900
            }
        }
    }

    // CREATE
    private static void createBook() {
        System.out.print("Nhập mã sách: ");
        String bookID = scanner.nextLine();

        System.out.print("Nhập tên sách: ");
        String bookName = scanner.nextLine();

        System.out.print("Nhập tên tác giả: ");
        String author = scanner.nextLine();

        Book book = new Book(bookID, bookName, author);
        bookList.add(book);
        System.out.println("✅ Thêm sách thành công.");
    }

    // READ
    private static void readBooks() {
        if (bookList.isEmpty()) {
            System.out.println("📭 Không có sách nào trong danh sách.");
            return;
        }

        System.out.println("\n📖 DANH SÁCH SÁCH:");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    // UPDATE
    private static void updateBook() {
        System.out.print("Nhập mã sách cần sửa: ");
        String bookID = scanner.nextLine();

        for (Book book : bookList) {
            if (book.getBookID().equals(bookID)) {
                System.out.print("Nhập tên sách mới: ");
                String newBookName = scanner.nextLine();
                book.setBookName(newBookName);

                System.out.print("Nhập tên tác giả mới: ");
                String newAuthor = scanner.nextLine();
                book.setAuthor(newAuthor);

                System.out.println("✅ Cập nhật sách thành công.");
                return;
            }
        }

        System.out.println("❌ Không tìm thấy sách với mã đã nhập.");
    }

    // DELETE
    private static void deleteBook() {
        System.out.print("Nhập mã sách cần xoá: ");
        String bookID = scanner.nextLine();

       Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookID().equals(bookID)) {
                iterator.remove();
                System.out.println("🗑️ Xoá sách thành công.");
                return;
            }
        }

        System.out.println("❌ Không tìm thấy sách để xoá.");
    }
}

