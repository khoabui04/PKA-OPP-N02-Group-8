package com.library.controller;

import com.library.model.Borrower;
import com.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public String listBorrowers(Model model) {
        model.addAttribute("borrowers", borrowerService.getAllBorrowers());
        return "borrowers/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("borrower", new Borrower());
        return "borrowers/add";
    }

    @PostMapping("/add")
    public String addBorrower(@ModelAttribute Borrower borrower) {
        borrowerService.addBorrower(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("borrower", borrowerService.getBorrowerById(id));
        return "borrowers/edit";
    }

    @PostMapping("/edit")
    public String editBorrower(@ModelAttribute Borrower borrower) {
        borrowerService.updateBorrower(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrower(@PathVariable("id") String id) {
        borrowerService.deleteBorrower(id);
        return "redirect:/borrowers";
    }
}