package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void testAddBook() {
        Book book = new Book("B001", "Lập trình Java", "Nguyễn Văn A", true);
        bookService.addBook(book);
        assertEquals(book, bookService.getBookById("B001"), "Sách được thêm thành công");
    }

    @Test
    void testUpdateBook() {
        Book book = new Book("B001", "Lập trình Java", "Nguyễn Văn A", true);
        bookService.addBook(book);
        Book updatedBook = new Book("B001", "Java Nâng cao", "Trần Thị B", false);
        bookService.updateBook(updatedBook);
        assertEquals("Java Nâng cao", bookService.getBookById("B001").getTitle(), "Tiêu đề sách được cập nhật");
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("B001", "Lập trình Java", "Nguyễn Văn A", true);
        bookService.addBook(book);
        bookService.deleteBook("B001");
        assertNull(bookService.getBookById("B001"), "Sách đã được xóa");
    }
}