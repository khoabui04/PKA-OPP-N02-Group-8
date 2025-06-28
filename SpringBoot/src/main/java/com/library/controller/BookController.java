package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping("/")
    public String home() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book("", "", "", true));
        return "book_form";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String showEditForm(@RequestParam String bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book_form";
    }

    @PostMapping("/books/edit")
    public String editBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam String bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

    @GetMapping("/books/near-due")
    public String listBooksNearDue(@RequestParam String borrowerId, Model model) {
        List<Book> books = borrowingRecordService.listBooksNearDueDate(borrowerId);
        model.addAttribute("books", books);
        model.addAttribute("borrowerId", borrowerId);
        return "near_due_books";
    }
}