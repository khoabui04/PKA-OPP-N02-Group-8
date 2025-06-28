package com.library.repository;

import com.library.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, String> {
    // Có thể thêm các phương thức custom nếu cần
}