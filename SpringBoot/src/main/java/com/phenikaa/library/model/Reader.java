package com.phenikaa.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tên độc giả không được để trống")
    @Column(nullable = false)
    private String name;
    
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    @Column(unique = true, nullable = false)
    private String email;
    
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại không hợp lệ")
    private String phone;
    
    private String address;
    
    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    private ReaderType readerType = ReaderType.STUDENT;
    
    @Enumerated(EnumType.STRING)
    private ReaderStatus status = ReaderStatus.ACTIVE;
    
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    
    @Column(name = "max_borrow_books")
    private Integer maxBorrowBooks = 5;
    
    @Column(name = "current_borrowed_books")
    private Integer currentBorrowedBooks = 0;
    
    private String notes;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Quan hệ với Borrowing
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Borrowing> borrowings;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        registrationDate = LocalDateTime.now();
        // Đặt ngày hết hạn 1 năm từ ngày đăng ký
        if (expiryDate == null) {
            expiryDate = LocalDate.now().plusYears(1);
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Business methods
    public boolean canBorrowBooks() {
        return status == ReaderStatus.ACTIVE && 
               currentBorrowedBooks < maxBorrowBooks &&
               expiryDate.isAfter(LocalDate.now());
    }
    
    public void borrowBook() {
        if (canBorrowBooks()) {
            currentBorrowedBooks++;
        }
    }
    
    public void returnBook() {
        if (currentBorrowedBooks > 0) {
            currentBorrowedBooks--;
        }
    }
    
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
    
    public enum ReaderType {
        STUDENT("Sinh viên"),
        TEACHER("Giảng viên"),
        STAFF("Nhân viên"),
        EXTERNAL("Bên ngoài");
        
        private final String displayName;
        
        ReaderType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum ReaderStatus {
        ACTIVE("Hoạt động"),
        SUSPENDED("Tạm khóa"),
        EXPIRED("Hết hạn"),
        BLOCKED("Bị chặn");
        
        private final String displayName;
        
        ReaderStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}