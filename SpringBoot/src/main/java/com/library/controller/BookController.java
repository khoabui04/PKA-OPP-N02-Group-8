package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.BorrowingRecordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String listBooks(Model model, Pageable pageable) {
        Page<Book> page = bookService.getAllBooks(pageable);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page", page);
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

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String keyword, Model model) {
        List<Book> books = bookService.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("books", books);
        return "book_list";
    }
}