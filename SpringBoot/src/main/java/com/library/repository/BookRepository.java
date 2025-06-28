package com.library.repository;

import com.library.model.Book;
import com.library.util.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends GenericRepository<Book> {
    public BookRepository() {
        super("books.dat");
    }

    public Book findById(String bookId) {
        return read(book -> book.getBookId().equals(bookId));
    }

    public void updateBook(Book book) {
        update(book, b -> b.getBookId().equals(book.getBookId()));
    }

    public void deleteBook(String bookId) {
        delete(book -> book.getBookId().equals(bookId));
    }
}