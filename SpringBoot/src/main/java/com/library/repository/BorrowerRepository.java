package com.library.repository;

import com.library.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, String> {
    // Có thể thêm các phương thức custom nếu cần
}