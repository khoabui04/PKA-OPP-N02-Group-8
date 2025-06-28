package com.library.service;

import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository = new BorrowerRepository();

    public void addBorrower(Borrower borrower) {
        try {
            borrowerRepository.create(borrower);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm người mượn: " + e.getMessage());
        }
    }

    public Borrower getBorrowerById(String borrowerId) {
        try {
            return borrowerRepository.findById(borrowerId);
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm người mượn: " + e.getMessage());
            return null;
        }
    }

    public void updateBorrower(Borrower updatedBorrower) {
        try {
            borrowerRepository.updateBorrower(updatedBorrower);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật người mượn: " + e.getMessage());
        }
    }

    public void deleteBorrower(String borrowerId) {
        try {
            borrowerRepository.deleteBorrower(borrowerId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa người mượn: " + e.getMessage());
        }
    }

    public List<Borrower> getAllBorrowers() {
        try {
            return borrowerRepository.readAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách người mượn: " + e.getMessage());
            return null;
        }
    }
}