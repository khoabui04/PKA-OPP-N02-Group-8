package com.phenikaa.library.repository;

import com.phenikaa.library.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    
    // Tìm kiếm theo username
    Optional<Librarian> findByUsername(String username);
    
    // Tìm kiếm theo email
    Optional<Librarian> findByEmail(String email);
    
    // Tìm kiếm theo mã nhân viên
    Optional<Librarian> findByEmployeeId(String employeeId);
    
    // Tìm kiếm theo vai trò
    List<Librarian> findByRole(Librarian.LibrarianRole role);
    
    // Tìm kiếm theo trạng thái
    List<Librarian> findByStatus(Librarian.LibrarianStatus status);
    
    // Tìm kiếm thủ thư đang hoạt động
    @Query("SELECT l FROM Librarian l WHERE l.status = 'ACTIVE'")
    List<Librarian> findActiveLibrarians();
    
    // Kiểm tra username đã tồn tại
    boolean existsByUsername(String username);
    
    // Kiểm tra email đã tồn tại
    boolean existsByEmail(String email);
}