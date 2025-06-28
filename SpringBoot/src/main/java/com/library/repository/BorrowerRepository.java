package com.library.repository;

import com.library.model.Borrower;
import com.library.util.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class BorrowerRepository extends GenericRepository<Borrower> {
    public BorrowerRepository() {
        super("borrowers.dat");
    }

    public Borrower findById(String borrowerId) {
        return read(borrower -> borrower.getBorrowerId().equals(borrowerId));
    }

    public void updateBorrower(Borrower borrower) {
        update(borrower, b -> b.getBorrowerId().equals(borrower.getBorrowerId()));
    }

    public void deleteBorrower(String borrowerId) {
        delete(borrower -> borrower.getBorrowerId().equals(borrowerId));
    }
}