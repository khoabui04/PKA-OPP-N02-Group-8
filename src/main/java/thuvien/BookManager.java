package thuvien;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
    }

    // Thêm sách mới
    public void addBook(Book book) {
        books.add(book);
    }

    // Xóa tất cả sách
    public void clearBooks() {
        books.clear();
    }

    // Hiển thị thông tin tất cả sách
    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong thư viện.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
