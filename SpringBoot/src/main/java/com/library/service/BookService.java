package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@ervice
public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public void addBook(Book book) {
        try {
            bookRepository.create(book);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác thêm sách.");
        }
    }

    public Book getBookById(String bookId) {
        try {
            return bookRepository.findById(bookId);
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm sách: " + e.getMessage());
            return null;
        } finally {
            System.out.println("Hoàn thành thao tác tìm sách.");
        }
    }

    public void updateBook(Book updatedBook) {
        try {
            bookRepository.updateBook(updatedBook);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác cập nhật sách.");
        }
    }

    public void deleteBook(String bookId) {
        try {
            bookRepository.deleteBook(bookId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác xóa sách.");
        }
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.readAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sách: " + e.getMessage());
            return null;
        }
    }
}