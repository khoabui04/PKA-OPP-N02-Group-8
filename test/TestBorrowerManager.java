import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class Borrower {
    private String borrowerName;
    public Borrower(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    public String getBorrowerName() {
        return borrowerName;
    }
}

class BorrowerCRUD {
    private List<Borrower> borrowers = new ArrayList<>();
    public void add(Borrower b) {
        borrowers.add(b);
    }
    public List<Borrower> getAll() {
        return borrowers;
    }
}

class BorrowerManager {
    public static BorrowerCRUD crud = new BorrowerCRUD();
}

public class TestBorrowerManager {

    @Test
    public void testSearchBorrowerByName() {
        // Reset dữ liệu
        BorrowerManager.crud = new BorrowerCRUD();

        // Thêm dữ liệu
        BorrowerManager.crud.add(new Borrower("Nguyen Van A"));
        BorrowerManager.crud.add(new Borrower("Tran Thi B"));
        BorrowerManager.crud.add(new Borrower("Le Van C"));

        // Tìm kiếm tên có trong danh sách
        boolean found = BorrowerManager.crud.getAll().stream()
            .anyMatch(b -> b.getBorrowerName().toLowerCase().contains("nguyen"));
        assertTrue(found);

        // Tìm kiếm tên không có trong danh sách
        boolean notFound = BorrowerManager.crud.getAll().stream()
            .noneMatch(b -> b.getBorrowerName().toLowerCase().contains("pham"));
        assertTrue(notFound);
    }
}