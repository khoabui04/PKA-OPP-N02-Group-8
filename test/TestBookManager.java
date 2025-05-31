import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp Book dùng cho kiểm thử
class Book {
    private final String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return id + " - " + title + " - " + author;
    }
}

// Lớp CrudManager dùng cho kiểm thử
class CrudManager<T> {
    private List<T> items = new ArrayList<>();

    public CrudManager(String filename) {
        // Không sử dụng file trong kiểm thử này
    }

    public List<T> getAll() {
        return items;
    }

    public void add(T item) {
        items.add(item);
    }
}

// Lớp kiểm thử chính
public class TestBookManager {
    private static final CrudManager<Book> crud = new CrudManager<>("books_test.dat");

    // Hàm lọc và hiển thị sách theo tác giả
    public static void filterBooksByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên tác giả cần lọc: ");
        String author = scanner.nextLine();
        boolean found = false;
        for (Book book : crud.getAll()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Không tìm thấy sách của tác giả này.");
        }
    }

    public static void main(String[] args) {
        // Xóa dữ liệu cũ và thêm dữ liệu mẫu để test
        crud.getAll().clear();
        crud.add(new Book("B001", "Lập trình Java", "Nguyễn Văn Anh"));
        crud.add(new Book("B002", "Cấu trúc dữ liệu", "Nguyễn Văn Bình"));
        crud.add(new Book("B003", "Giải thuật", "Nguyễn Văn Công"));

        filterBooksByAuthor();
    }
}