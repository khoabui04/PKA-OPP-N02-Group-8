package com.phenikaa.library.service;

import com.phenikaa.library.model.Reader;
import com.phenikaa.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReaderService {
    
    @Autowired
    private ReaderRepository readerRepository;
    
    // CRUD Operations
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
    
    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }
    
    public Reader saveReader(Reader reader) {
        validateReader(reader);
        return readerRepository.save(reader);
    }
    
    public Reader updateReader(Long id, Reader readerDetails) {
        return readerRepository.findById(id)
            .map(reader -> {
                reader.setName(readerDetails.getName());
                reader.setEmail(readerDetails.getEmail());
                reader.setPhone(readerDetails.getPhone());
                reader.setAddress(readerDetails.getAddress());
                reader.setStudentId(readerDetails.getStudentId());
                reader.setDateOfBirth(readerDetails.getDateOfBirth());
                reader.setReaderType(readerDetails.getReaderType());
                reader.setStatus(readerDetails.getStatus());
                reader.setExpiryDate(readerDetails.getExpiryDate());
                reader.setMaxBorrowBooks(readerDetails.getMaxBorrowBooks());
                reader.setNotes(readerDetails.getNotes());
                
                return readerRepository.save(reader);
            })
            .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả với ID: " + id));
    }
    
    public void deleteReader(Long id) {
        Reader reader = readerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả với ID: " + id));
        
        // Kiểm tra xem độc giả có đang mượn sách không
        if (reader.getCurrentBorrowedBooks() > 0) {
            throw new RuntimeException("Không thể xóa độc giả đang mượn sách");
        }
        
        readerRepository.delete(reader);
    }
    
    // Search Operations
    public Optional<Reader> findByEmail(String email) {
        return readerRepository.findByEmail(email);
    }
    
    public Optional<Reader> findByStudentId(String studentId) {
        return readerRepository.findByStudentId(studentId);
    }
    
    public List<Reader> searchReadersByName(String name) {
        return readerRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Page<Reader> searchReaders(String name, String email, Reader.ReaderType readerType, 
                                     Reader.ReaderStatus status, Pageable pageable) {
        return readerRepository.searchReaders(name, email, readerType, status, pageable);
    }
    
    // Business Logic
    public List<Reader> getReadersByType(Reader.ReaderType type) {
        return readerRepository.findByReaderType(type);
    }
    
    public List<Reader> getReadersByStatus(Reader.ReaderStatus status) {
        return readerRepository.findByStatus(status);
    }
    
    public List<Reader> getExpiredReaders() {
        return readerRepository.findByExpiryDateBefore(LocalDate.now());
    }
    
    public List<Reader> getReadersExpiringSoon(int days) {
        LocalDate endDate = LocalDate.now().plusDays(days);
        return readerRepository.findByExpiryDateBetween(LocalDate.now(), endDate);
    }
    
    public List<Reader> getReadersWithOverdueBooks() {
        return readerRepository.findReadersWithOverdueBooks();
    }
    
    public List<Reader> getTopBorrowers(int limit) {
        return readerRepository.findTopBorrowers(Pageable.ofSize(limit));
    }
    
    public List<Reader> getRecentlyRegistered(int limit) {
        return readerRepository.findRecentlyRegistered(Pageable.ofSize(limit));
    }
    
    // Statistics
    public Long getTotalReadersCount() {
        return readerRepository.countTotalReaders();
    }
    
    public Long getActiveReadersCount() {
        return readerRepository.countActiveReaders();
    }
    
    public Long getSuspendedReadersCount() {
        return readerRepository.countByStatus(Reader.ReaderStatus.SUSPENDED);
    }
    
    public Long getNewReadersThisMonth() {
        return readerRepository.countNewReadersThisMonth();
    }
    
    public Long getReadersWithBorrowedBooksCount() {
        return readerRepository.countReadersWithBorrowedBooks();
    }
    
    // Validation
    private void validateReader(Reader reader) {
        if (reader.getName() == null || reader.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên độc giả không được để trống");
        }
        
        if (reader.getEmail() == null || reader.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        
        // Kiểm tra email format
        if (!isValidEmail(reader.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        
        // Kiểm tra email đã tồn tại
        if (reader.getId() == null && readerRepository.existsByEmail(reader.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }
        
        // Kiểm tra mã sinh viên nếu có
        if (reader.getStudentId() != null && !reader.getStudentId().trim().isEmpty()) {
            if (reader.getId() == null && readerRepository.existsByStudentId(reader.getStudentId())) {
                throw new IllegalArgumentException("Mã sinh viên đã tồn tại trong hệ thống");
            }
        }
        
        // Kiểm tra số điện thoại nếu có
        if (reader.getPhone() != null && !reader.getPhone().trim().isEmpty()) {
            if (!isValidPhone(reader.getPhone())) {
                throw new IllegalArgumentException("Số điện thoại không hợp lệ");
            }
            if (reader.getId() == null && readerRepository.existsByPhone(reader.getPhone())) {
                throw new IllegalArgumentException("Số điện thoại đã tồn tại trong hệ thống");
            }
        }
        
        if (reader.getMaxBorrowBooks() == null || reader.getMaxBorrowBooks() <= 0) {
            throw new IllegalArgumentException("Số sách tối đa được mượn phải lớn hơn 0");
        }
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean isValidPhone(String phone) {
        return phone.matches("^[0-9]{10,11}$");
    }
    
    // Business methods for borrowing
    @Transactional
    public boolean canBorrowMoreBooks(Long readerId) {
        Optional<Reader> readerOpt = readerRepository.findById(readerId);
        return readerOpt.map(Reader::canBorrowBooks).orElse(false);
    }
    
    @Transactional
    public void borrowBook(Long readerId) {
        Reader reader = readerRepository.findById(readerId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả với ID: " + readerId));
        
        if (reader.canBorrowBooks()) {
            reader.borrowBook();
            readerRepository.save(reader);
        } else {
            throw new RuntimeException("Độc giả không thể mượn thêm sách");
        }
    }
    
    @Transactional
    public void returnBook(Long readerId) {
        Reader reader = readerRepository.findById(readerId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả với ID: " + readerId));
        
        reader.returnBook();
        readerRepository.save(reader);
    }
    
    @Transactional
    public void renewMembership(Long readerId, int months) {
        Reader reader = readerRepository.findById(readerId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả với ID: " + readerId));
        
        LocalDate newExpiryDate = reader.getExpiryDate().isAfter(LocalDate.now()) 
            ? reader.getExpiryDate().plusMonths(months)
            : LocalDate.now().plusMonths(months);
            
        reader.setExpiryDate(newExpiryDate);
        reader.setStatus(Reader.ReaderStatus.ACTIVE);
        readerRepository.save(reader);
    }
}