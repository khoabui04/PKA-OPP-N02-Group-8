package com.library;

import com.library.model.Borrower;
import com.library.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowerServiceTest {
    private BorrowerService borrowerService;

    @BeforeEach
    void setUp() {
        borrowerService = new BorrowerService();
    }

    @Test
    void testAddBorrower() {
        Borrower borrower = new Borrower("U001", "Nguyễn Thị B", "b@example.com");
        borrowerService.addBorrower(borrower);
        assertEquals(borrower, borrowerService.getBorrowerById("U001"), "Người mượn được thêm thành công");
    }

    @Test
    void testUpdateBorrower() {
        Borrower borrower = new Borrower("U001", "Nguyễn Thị B", "b@example.com");
        borrowerService.addBorrower(borrower);
        Borrower updatedBorrower = new Borrower("U001", "Trần Văn C", "c@example.com");
        borrowerService.updateBorrower(updatedBorrower);
        assertEquals("Trần Văn C", borrowerService.getBorrowerById("U001").getName(), "Tên người mượn được cập nhật");
    }

    @Test
    void testDeleteBorrower() {
        Borrower borrower = new Borrower("U001", "Nguyễn Thị B", "b@example.com");
        borrowerService.addBorrower(borrower);
        borrowerService.deleteBorrower("U001");
        assertNull(borrowerService.getBorrowerById("U001"), "Người mượn đã được xóa");
    }
}