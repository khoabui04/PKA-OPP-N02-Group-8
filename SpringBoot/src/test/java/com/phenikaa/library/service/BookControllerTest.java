package com.phenikaa.library.controller;

import com.phenikaa.library.model.Book;
import com.phenikaa.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    @Test
    void testListBooks() {
        BookService service = Mockito.mock(BookService.class);
        BookController controller = new BookController(service);
        Model model = Mockito.mock(Model.class);
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));
        String view = controller.listBooks(model);
        assertEquals("books/list", view);
        Mockito.verify(model).addAttribute(Mockito.eq("books"), Mockito.any());
    }
}