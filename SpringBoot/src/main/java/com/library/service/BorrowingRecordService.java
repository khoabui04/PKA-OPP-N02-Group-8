package com.library.service;

import com.library.model.Book;
import com.library.model.BorrowingRecord;
import com.library.repository.BookRepository;
import com.library.repository.BorrowingRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository = new BorrowingRecordRepository();
    private final BookRepository bookRepository = new BookRepository();

    public List<BorrowingRecord> getAllRecords() {
        try {
            return borrowingRecordRepository.readAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách bản ghi mượn: " + e.getMessage());
            return null;
        }
    }

    public void addRecord(BorrowingRecord record) {
        try {
            borrowingRecordRepository.create(record);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm bản ghi mượn: " + e.getMessage());
        }
    }

    public BorrowingRecord getRecordById(String recordId) {
        try {
            return borrowingRecordRepository.findById(recordId);
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm bản ghi mượn: " + e.getMessage());
            return null;
        }
    }

    public void updateRecord(BorrowingRecord updatedRecord) {
        try {
            borrowingRecordRepository.updateBorrowingRecord(updatedRecord);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật bản ghi mượn: " + e.getMessage());
        }
    }

    public void deleteRecord(String recordId) {
        try {
            borrowingRecordRepository.deleteBorrowingRecord(recordId);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa bản ghi mượn: " + e.getMessage());
        }
    }

    public List<Book> listBorrowedBooks(String borrowerId) {
        return borrowingRecordRepository.readAll().stream()
                .filter(r -> r.getBorrowerId().equals(borrowerId) && !r.isReturned())
                .map(r -> bookRepository.findById(r.getBookId()))
                .collect(Collectors.toList());
    }

    public List<Book> listBooksNearDueDate(String borrowerId) {
        LocalDate now = LocalDate.now();
        LocalDate nearDue = now.plusDays(3);
        return borrowingRecordRepository.readAll().stream()
                .filter(r -> r.getBorrowerId().equals(borrowerId))
                .filter(r -> !r.isReturned())
                .filter(r -> r.getDueDate().isAfter(now) && r.getDueDate().isBefore(nearDue))
                .map(r -> bookRepository.findById(r.getBookId()))
                .collect(Collectors.toList());
    }
}