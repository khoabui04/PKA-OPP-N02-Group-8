package com.phenikaa.library.controller;

import com.phenikaa.library.service.BookService;
import com.phenikaa.library.service.ReaderService;
import com.phenikaa.library.service.BorrowingService;
import com.phenikaa.library.service.NotificationService;
import com.phenikaa.library.model.Book;
import com.phenikaa.library.model.Reader;
import com.phenikaa.library.model.Borrowing;
import com.phenikaa.library.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReaderService readerService;
    
    @Autowired
    private BorrowingService borrowingService;
    
    @Autowired
    private NotificationService notificationService;
    
    // Trang chủ Dashboard
    @GetMapping("/")
    public String dashboard(Model model) {
        // Thống kê tổng quan
        Map<String, Object> stats = getDashboardStats();
        model.addAttribute("stats", stats);
        
        // Dữ liệu gần đây
        List<Book> recentBooks = bookService.getRecentlyAddedBooks(5);
        List<Reader> recentReaders = readerService.getRecentlyRegistered(5);
        List<Borrowing> recentBorrowings = borrowingService.getRecentBorrowings(10);
        List<Borrowing> overdueBorrowings = borrowingService.getOverdueBorrowings();
        List<Borrowing> dueSoonBorrowings = borrowingService.getBorrowingsDueSoon(7);
        
        model.addAttribute("recentBooks", recentBooks);
        model.addAttribute("recentReaders", recentReaders);
        model.addAttribute("recentBorrowings", recentBorrowings);
        model.addAttribute("overdueBorrowings", overdueBorrowings);
        model.addAttribute("dueSoonBorrowings", dueSoonBorrowings);
        
        // Thông báo
        List<Notification> recentNotifications = notificationService.getRecentNotifications(10);
        model.addAttribute("notifications", recentNotifications);
        
        return "dashboard/index";
    }
    
    // Trang quản lý thông báo
    @GetMapping("/notifications")
    public String notifications(Model model) {
        List<Notification> allNotifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        return "dashboard/notifications";
    }
    
    // Cài đặt hệ thống
    @GetMapping("/settings")
    public String settings(Model model) {
        // Thông tin cấu hình hệ thống
        Map<String, Object> systemSettings = new HashMap<>();
        systemSettings.put("libraryName", "Thư viện Đại học Phenikaa");
        systemSettings.put("defaultBorrowDays", 14);
        systemSettings.put("maxRenewals", 2);
        systemSettings.put("finePerDay", 5000);
        systemSettings.put("maxBooksPerReader", 5);
        
        model.addAttribute("settings", systemSettings);
        return "dashboard/settings";
    }
    
    // Thông tin thủ thư
    @GetMapping("/librarian-info")
    public String librarianInfo(Model model) {
        // Thông tin thủ thư hiện tại (tạm thời hardcode)
        Map<String, Object> librarianInfo = new HashMap<>();
        librarianInfo.put("name", "Nguyễn Văn Thành");
        librarianInfo.put("email", "thanh.nguyen@phenikaa.edu.vn");
        librarianInfo.put("phone", "0123456789");
        librarianInfo.put("employeeId", "LIB001");
        librarianInfo.put("department", "Thư viện Trung tâm");
        librarianInfo.put("role", "Thủ thư chính");
        librarianInfo.put("workShift", "Ca sáng (8:00 - 17:00)");
        
        model.addAttribute("librarian", librarianInfo);
        return "dashboard/librarian-info";
    }
    
    // Tìm kiếm nâng cao
    @GetMapping("/advanced-search")
    public String advancedSearch(Model model) {
        // Chuẩn bị dữ liệu cho form tìm kiếm
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("publishers", bookService.getAllPublishers());
        model.addAttribute("readerTypes", Reader.ReaderType.values());
        model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
        model.addAttribute("borrowStatuses", Borrowing.BorrowStatus.values());
        
        return "dashboard/advanced-search";
    }
    
    // Báo cáo thống kê
    @GetMapping("/reports")
    public String reports(Model model) {
        Map<String, Object> stats = getDashboardStats();
        model.addAttribute("stats", stats);
        
        // Thống kê theo thời gian (tạm thời)
        model.addAttribute("monthlyStats", getMonthlyStats());
        
        return "dashboard/reports";
    }
    
    // REST API cho Dashboard
    @RestController
    @RequestMapping("/api/dashboard")
    public static class DashboardRestController {
        
        @Autowired
        private BookService bookService;
        
        @Autowired
        private ReaderService readerService;
        
        @Autowired
        private BorrowingService borrowingService;
        
        @Autowired
        private NotificationService notificationService;
        
        @GetMapping("/stats")
        public ResponseEntity<Map<String, Object>> getDashboardStats() {
            Map<String, Object> stats = new HashMap<>();
            
            // Thống kê sách
            Map<String, Object> bookStats = new HashMap<>();
            bookStats.put("totalBooks", bookService.getTotalBooksCount());
            bookStats.put("availableBooks", bookService.getAvailableBooksCount());
            bookStats.put("borrowedBooks", bookService.getBorrowedBooksCount());
            stats.put("books", bookStats);
            
            // Thống kê độc giả
            Map<String, Object> readerStats = new HashMap<>();
            readerStats.put("totalReaders", readerService.getTotalReadersCount());
            readerStats.put("activeReaders", readerService.getActiveReadersCount());
            readerStats.put("readersWithBorrowedBooks", readerService.getReadersWithBorrowedBooksCount());
            stats.put("readers", readerStats);
            
            // Thống kê mượn/trả
            Map<String, Object> borrowingStats = new HashMap<>();
            borrowingStats.put("totalBorrowings", borrowingService.getTotalBorrowingsCount());
            borrowingStats.put("currentBorrowings", borrowingService.getCurrentBorrowingsCount());
            borrowingStats.put("overdueBorrowings", borrowingService.getOverdueBorrowingsCount());
            borrowingStats.put("returnedBorrowings", borrowingService.getReturnedBorrowingsCount());
            stats.put("borrowings", borrowingStats);
            
            return ResponseEntity.ok(stats);
        }
        
        @GetMapping("/recent-activities")
        public ResponseEntity<Map<String, Object>> getRecentActivities() {
            Map<String, Object> activities = new HashMap<>();
            
            activities.put("recentBooks", bookService.getRecentlyAddedBooks(5));
            activities.put("recentReaders", readerService.getRecentlyRegistered(5));
            activities.put("recentBorrowings", borrowingService.getRecentBorrowings(10));
            activities.put("overdueBorrowings", borrowingService.getOverdueBorrowings());
            activities.put("dueSoonBorrowings", borrowingService.getBorrowingsDueSoon(7));
            
            return ResponseEntity.ok(activities);
        }
        
        @GetMapping("/notifications")
        public ResponseEntity<List<Notification>> getNotifications() {
            List<Notification> notifications = notificationService.getRecentNotifications(20);
            return ResponseEntity.ok(notifications);
        }
        
        @PostMapping("/notifications/{id}/read")
        public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long id) {
            try {
                notificationService.markAsRead(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }
    
    // Helper methods
    private Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Thống kê sách
        Map<String, Object> bookStats = new HashMap<>();
        bookStats.put("totalBooks", bookService.getTotalBooksCount());
        bookStats.put("availableBooks", bookService.getAvailableBooksCount());
        bookStats.put("borrowedBooks", bookService.getBorrowedBooksCount());
        stats.put("books", bookStats);
        
        // Thống kê độc giả
        Map<String, Object> readerStats = new HashMap<>();
        readerStats.put("totalReaders", readerService.getTotalReadersCount());
        readerStats.put("activeReaders", readerService.getActiveReadersCount());
        readerStats.put("readersWithBorrowedBooks", readerService.getReadersWithBorrowedBooksCount());
        stats.put("readers", readerStats);
        
        // Thống kê mượn/trả
        Map<String, Object> borrowingStats = new HashMap<>();
        borrowingStats.put("totalBorrowings", borrowingService.getTotalBorrowingsCount());
        borrowingStats.put("currentBorrowings", borrowingService.getCurrentBorrowingsCount());
        borrowingStats.put("overdueBorrowings", borrowingService.getOverdueBorrowingsCount());
        borrowingStats.put("returnedBorrowings", borrowingService.getReturnedBorrowingsCount());
        stats.put("borrowings", borrowingStats);
        
        return stats;
    }
    
    private Map<String, Object> getMonthlyStats() {
        // Tạm thời trả về dữ liệu mẫu
        Map<String, Object> monthlyStats = new HashMap<>();
        monthlyStats.put("january", Map.of("borrowed", 120, "returned", 115));
        monthlyStats.put("february", Map.of("borrowed", 135, "returned", 130));
        monthlyStats.put("march", Map.of("borrowed", 150, "returned", 145));
        return monthlyStats;
    }
}