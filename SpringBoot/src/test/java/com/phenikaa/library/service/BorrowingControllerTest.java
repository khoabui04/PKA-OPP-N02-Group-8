package com.phenikaa.library.controller;

import com.phenikaa.library.model.Borrowing;
import com.phenikaa.library.service.BorrowingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingControllerTest {
    @Test
    void testListBorrowings() {
        BorrowingService service = Mockito.mock(BorrowingService.class);
        BorrowingController controller = new BorrowingController(service);
        Model model = Mockito.mock(Model.class);
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(new Borrowing(), new Borrowing()));
        String view = controller.listBorrowings(model);
        assertEquals("borrowings/list", view);
        Mockito.verify(model).addAttribute(Mockito.eq("borrowings"), Mockito.any());
    }
}