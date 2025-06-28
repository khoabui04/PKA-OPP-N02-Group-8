package com.library;

import com.library.model.Book;
import com.library.model.BorrowingRecord;
import com.library.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowingRecordServiceTest {
    private BorrowingRecordService service;

    @BeforeEach
    void setUp() {
        service = new BorrowingRecordService();
        Book book = new Book("B001", "Lập trình Java", "Nguyễn Văn A", false);
        BorrowingRecord record = new BorrowingRecord("R001", "B001", "U001", LocalDate.now(), LocalDate.now().plusDays(7));
        service.getBookRepository().create(book);
        service.getRecordRepository().create(record);
    }

    @Test
    void testListBorrowedBooks() {
        List<Book> books = service.listBorrowedBooks("U001");
        assertFalse(books.isEmpty(), "Phải trả về ít nhất một cuốn sách");
        assertEquals("Lập trình Java", books.get(0).getTitle(), "Tiêu đề sách phải khớp");
    }

    @Test
    void testListBooksNearDueDate() {
        BorrowingRecord record = new BorrowingRecord("R002", "B001", "U001", LocalDate.now(), LocalDate.now().plusDays(2));
        service.getRecordRepository().create(record);
        List<Book> books = service.listBooksNearDueDate("U001");
        assertFalse(books.isEmpty(), "Phải trả về sách sắp đến hạn");
        assertEquals("Lập trình Java", books.get(0).getTitle(), "Tiêu đề sách phải khớp");
    }
}