package com.library.controller;

import com.library.model.BorrowingRecord;
import com.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/records")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public String listRecords(Model model) {
        try {
            List<BorrowingRecord> records = borrowingRecordService.getAllRecords();
            model.addAttribute("records", records);
            return "records/list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách bản ghi mượn: " + e.getMessage());
            return "error";
        } finally {
            System.out.println("Hoàn thành yêu cầu liệt kê bản ghi mượn.");
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("record", new BorrowingRecord());
        return "records/add";
    }

    @PostMapping("/add")
    public String addRecord(@ModelAttribute BorrowingRecord record) {
        borrowingRecordService.addRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("record", borrowingRecordService.getRecordById(id));
        return "records/edit";
    }

    @PostMapping("/edit")
    public String editRecord(@ModelAttribute BorrowingRecord record) {
        borrowingRecordService.updateRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable("id") String id) {
        borrowingRecordService.deleteRecord(id);
        return "redirect:/records";
    }
}