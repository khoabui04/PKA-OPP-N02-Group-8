package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) { bookRepository.save(book); }
    public Book getBookById(String bookId) { return bookRepository.findById(bookId).orElse(null); }
    public void updateBook(Book book) { bookRepository.save(book); }
    public void deleteBook(String bookId) { bookRepository.deleteById(bookId); }
    public List<Book> getAllBooks() { return bookRepository.findAll(); }
    public List<Book> searchBooks(String keyword) { return bookRepository.findByTitleContainingIgnoreCase(keyword); }
}