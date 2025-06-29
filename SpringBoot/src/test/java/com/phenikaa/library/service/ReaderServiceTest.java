package com.phenikaa.library.service;

import com.phenikaa.library.model.Reader;
import com.phenikaa.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReaderServiceTest {
    @Test
    void testFindReaderById() {
        ReaderRepository repo = Mockito.mock(ReaderRepository.class);
        ReaderService service = new ReaderService(repo);
        Reader reader = new Reader();
        reader.setId(1L);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(reader));
        assertEquals(1L, service.findById(1L).getId());
    }
}