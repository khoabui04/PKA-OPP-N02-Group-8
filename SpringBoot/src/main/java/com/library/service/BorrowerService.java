package com.library.service;

import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import java.util.ArrayList;
import java.util.List;

public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService() {
        this.borrowerRepository = new BorrowerRepository();
    }

    public void addBorrower(Borrower borrower) {
        try {
            borrowerRepository.create(borrower);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm người mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác thêm người mượn.");
        }
    }

    public Borrower getBorrowerById(String borrowerId) {
        try {
            return borrowerRepository.findById(borrowerId);
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm người mượn: " + e.getMessage());
            return null;
        } finally {
            System.out.println("Hoàn thành thao tác tìm người mượn.");
        }
    }

    public void updateBorrower(Borrower updatedBorrower) {
        try {
            borrowerRepository.updateBorrower(updatedBorrower);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật người mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác cập nhật người mượn.");
        }
    }

    public void deleteBorrower(String borrowerId) {
        try {
            borrowerRepository.deleteBorrower(borrowerId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa người mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác xóa người mượn.");
        }
    }

    public List<Borrower> getAllBorrowers() {
        try {
            return borrowerRepository.readAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách người mượn: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            System.out.println("Hoàn thành thao tác lấy danh sách người mượn.");
        }
    }
}