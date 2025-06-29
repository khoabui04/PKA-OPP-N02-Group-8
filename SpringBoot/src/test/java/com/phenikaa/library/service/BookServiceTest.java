package com.phenikaa.library.service;

import com.phenikaa.library.model.Book;
import com.phenikaa.library.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    @Test
    void testFindBookById() {
        BookRepository repo = Mockito.mock(BookRepository.class);
        BookService service = new BookService(repo);
        Book book = new Book();
        book.setId(1L);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(book));
        assertEquals(1L, service.findById(1L).getId());
    }
}