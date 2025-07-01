package com.phenikaa.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tiêu đề sách không được để trống")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Tác giả không được để trống")
    @Column(nullable = false)
    private String author;
    
    @Column(unique = true)
    private String isbn;
    
    @NotBlank(message = "Thể loại không được để trống")
    @Column(nullable = false)
    private String category;
    
    private String publisher;
    
    @Column(name = "publication_year")
    private Integer publicationYear;
    
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được âm")
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(name = "available_quantity")
    private Integer availableQuantity;
    
    private String description;
    
    @Column(name = "shelf_location")
    private String shelfLocation;
    
    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Quan hệ với Borrowing
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Borrowing> borrowings;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (availableQuantity == null) {
            availableQuantity = quantity;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Business methods
    public boolean isAvailable() {
        return availableQuantity > 0 && status == BookStatus.AVAILABLE;
    }
    
    public void borrowBook() {
        if (availableQuantity > 0) {
            availableQuantity--;
        }
    }
    
    public void returnBook() {
        if (availableQuantity < quantity) {
            availableQuantity++;
        }
    }
    
    public enum BookStatus {
        AVAILABLE("Có sẵn"),
        BORROWED("Đã mượn"),
        MAINTENANCE("Bảo trì"),
        LOST("Mất sách");
        
        private final String displayName;
        
        BookStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}