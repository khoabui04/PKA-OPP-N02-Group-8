package com.phenikaa.library.controller;

import com.phenikaa.library.model.Reader;
import com.phenikaa.library.service.ReaderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReaderControllerTest {
    @Test
    void testListReaders() {
        ReaderService service = Mockito.mock(ReaderService.class);
        ReaderController controller = new ReaderController(service);
        Model model = Mockito.mock(Model.class);
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(new Reader(), new Reader()));
        String view = controller.listReaders(model);
        assertEquals("readers/list", view);
        Mockito.verify(model).addAttribute(Mockito.eq("readers"), Mockito.any());
    }
}