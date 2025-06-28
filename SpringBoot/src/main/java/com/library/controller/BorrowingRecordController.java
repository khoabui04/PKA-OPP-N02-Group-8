package com.library.controller;

import com.library.model.BorrowingRecord;
import com.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService service;

    @GetMapping("/borrowing-records")
    public String listBorrowingRecords(Model model) {
        try {
            List<BorrowingRecord> records = service.getRecordRepository().readAll();
            model.addAttribute("records", records);
            return "borrowing_record";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách bản ghi mượn: " + e.getMessage());
            return "error";
        } finally {
            System.out.println("Hoàn thành yêu cầu liệt kê bản ghi mượn.");
        }
    }
}