import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


// Lớp BorrowingSlip dùng cho kiểm thử
class BorrowingSlip {
    private final String slipID;
    private Borrower borrower;
    private Book book;
    private Date borrowDate;
    private Date dueDate;

    public BorrowingSlip(String slipID, Borrower borrower, Book book, Date borrowDate, Date dueDate) {
        this.slipID = slipID;
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return slipID + " | " + borrower + " | " + book + " | " + borrowDate + " | " + dueDate;
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
public class TestBorrowingSlipManager {
    private static final CrudManager<BorrowingSlip> crud = new CrudManager<>("slips_test.dat");

    public static void createSlip() {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("✅ Thêm phiếu mượn thành công.");
        System.out.println("Danh sách phiếu mượn hiện tại:");
        for (BorrowingSlip s : crud.getAll()) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        crud.getAll().clear();
        createSlip();
    }
}