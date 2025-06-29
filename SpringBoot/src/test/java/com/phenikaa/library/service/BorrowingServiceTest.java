package com.phenikaa.library.service;

import com.phenikaa.library.model.Borrowing;
import com.phenikaa.library.repository.BorrowingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingServiceTest {
    @Test
    void testFindBorrowingById() {
        BorrowingRepository repo = Mockito.mock(BorrowingRepository.class);
        BorrowingService service = new BorrowingService(repo);
        Borrowing borrowing = new Borrowing();
        borrowing.setId(1L);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(borrowing));
        assertEquals(1L, service.findById(1L).getId());
    }
}