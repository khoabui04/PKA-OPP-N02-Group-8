package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        try {
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            return "book_list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách sách: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/books/near-due")
    public String listBooksNearDue(@RequestParam String borrowerId, Model model) {
        try {
            List<Book> books = borrowingRecordService.listBooksNearDueDate(borrowerId);
            model.addAttribute("books", books);
            model.addAttribute("borrowerId", borrowerId);
            return "near_due_books";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách sách: " + e.getMessage());
            return "error";
        } finally {
            System.out.println("Hoàn thành yêu cầu liệt kê sách sắp đến hạn.");
        }
    }
}