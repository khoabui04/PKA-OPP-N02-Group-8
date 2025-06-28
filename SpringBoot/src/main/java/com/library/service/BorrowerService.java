package com.library.service;

import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    public void addBorrower(Borrower borrower) { borrowerRepository.save(borrower); }
    public Borrower getBorrowerById(String borrowerId) { return borrowerRepository.findById(borrowerId).orElse(null); }
    public void updateBorrower(Borrower borrower) { borrowerRepository.save(borrower); }
    public void deleteBorrower(String borrowerId) { borrowerRepository.deleteById(borrowerId); }
    public List<Borrower> getAllBorrowers() { return borrowerRepository.findAll(); }
}