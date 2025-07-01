package com.phenikaa.library.controller;

import com.phenikaa.library.model.Book;
import com.phenikaa.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @GetMapping("/advanced-search")
    public String advancedSearch(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            Model model) {
        List<Book> books = bookService.advancedSearch(title, author, category, isbn);
        model.addAttribute("books", books);
        return "search/advanced";
    }
}