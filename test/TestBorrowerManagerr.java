import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp Borrower dùng cho kiểm thử
class Borrower {
private final String id;
private String borrowerName;
private String phone;

public Borrower(String id, String borrowerName, String phone) {
this.id = id;
this.borrowerName = borrowerName;
this.phone = phone;
}

public String getBorrowerName() {
return borrowerName;
}

@Override
public String toString() {
return id + " - " + borrowerName + " - " + phone;
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
public class TestBorrowerManagerr {
private static final CrudManager<Borrower> crud = new CrudManager<>("borrowers_test.dat");

public static void searchBorrowerByName() {
Scanner scanner = new Scanner(System.in);
System.out.print("Nhập tên người mượn cần tìm: ");
String name = scanner.nextLine();
boolean found = false;
for (Borrower b : crud.getAll()) {
if (b.getBorrowerName().toLowerCase().contains(name.toLowerCase())) {
System.out.println(b);
found = true;
}
}
if (!found) {
System.out.println("❌ Không tìm thấy người mượn này.");
}
}

public static void main(String[] args) {
// Xóa dữ liệu cũ nếu có và thêm dữ liệu mẫu
crud.getAll().clear();
crud.add(new Borrower("BR001", "Nguyễn Văn Tịnh", "0123456789"));
crud.add(new Borrower("BR002", "Trần Thị Bảy", "0987654321"));
crud.add(new Borrower("BR003", "Nguyễn Văn Hinh ", "0111222333"));

searchBorrowerByName();
}
}