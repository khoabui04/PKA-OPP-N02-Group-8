package com.library.controller;

import com.library.model.Borrower;
import com.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BorrowerController {
    @Autowired
    private BorrowerService service;

    @GetMapping("/borrowers")
    public String listBorrowers(Model model) {
        try {
            List<Borrower> borrowers = service.getAllBorrowers();
            model.addAttribute("borrowers", borrowers);
            return "borrower_list";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách người mượn: " + e.getMessage());
            return "error";
        } finally {
            System.out.println("Hoàn thành yêu cầu liệt kê người mượn.");
        }
    }
}