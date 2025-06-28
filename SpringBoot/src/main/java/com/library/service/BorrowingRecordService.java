package com.library.service;

import com.library.model.Book;
import com.library.model.BorrowingRecord;
import com.library.repository.BorrowingRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository = new BorrowingRecordRepository();

    public List<BorrowingRecord> getAllRecords() {
        try {
            return borrowingRecordRepository.readAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách bản ghi mượn: " + e.getMessage());
            return null;
        }
    }

    public List<Book> listBooksNearDueDate(String borrowerId) {
        LocalDate now = LocalDate.now();
        LocalDate nearDue = now.plusDays(3);
        return borrowingRecordRepository.readAll().stream()
                .filter(r -> r.getBorrower().getBorrowerId().equals(borrowerId))
                .filter(r -> !r.isReturned())
                .filter(r -> r.getDueDate().isAfter(now) && r.getDueDate().isBefore(nearDue))
                .map(BorrowingRecord::getBook)
                .collect(Collectors.toList());
    }
}