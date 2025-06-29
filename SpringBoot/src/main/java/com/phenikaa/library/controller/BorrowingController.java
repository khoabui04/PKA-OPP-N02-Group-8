package com.phenikaa.library.controller;

import com.phenikaa.library.model.Borrowing;
import com.phenikaa.library.model.Book;
import com.phenikaa.library.model.Reader;
import com.phenikaa.library.model.Librarian;
import com.phenikaa.library.service.BorrowingService;
import com.phenikaa.library.service.BookService;
import com.phenikaa.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {
    
    @Autowired
    private BorrowingService borrowingService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReaderService readerService;
    
    // Hiển thị danh sách giao dịch mượn/trả
    @GetMapping
    public String listBorrowings(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "borrowDate") String sortBy,
                                @RequestParam(defaultValue = "desc") String sortDir,
                                @RequestParam(required = false) Long readerId,
                                @RequestParam(required = false) Long bookId,
                                @RequestParam(required = false) String status,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Borrowing.BorrowStatus borrowStatus = null;
        try {
            if (status != null && !status.trim().isEmpty()) {
                borrowStatus = Borrowing.BorrowStatus.valueOf(status);
            }
        } catch (IllegalArgumentException e) {
            // Invalid enum value, ignore
        }
        
        Page<Borrowing> borrowingPage = borrowingService.searchBorrowings(
            readerId, bookId, borrowStatus, startDate, endDate, pageable);
        
        model.addAttribute("borrowings", borrowingPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", borrowingPage.getTotalPages());
        model.addAttribute("totalElements", borrowingPage.getTotalElements());
        model.addAttribute("borrowStatuses", Borrowing.BorrowStatus.values());
        model.addAttribute("readers", readerService.getAllReaders());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("currentReaderId", readerId);
        model.addAttribute("currentBookId", bookId);
        model.addAttribute("currentStatus", status);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "borrowings/list";
    }
    
    // Hiển thị form mượn sách mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("borrowing", new Borrowing());
        model.addAttribute("readers", readerService.getAllReaders());
        model.addAttribute("availableBooks", bookService.getAvailableBooks());
        return "borrowings/form";
    }
    
    // Xử lý mượn sách mới
    @PostMapping
    public String createBorrowing(@RequestParam Long bookId,
                                 @RequestParam Long readerId,
                                 Model model) {
        try {
            // Tạm thời sử dụng librarian giả - trong thực tế sẽ lấy từ session
            Librarian currentLibrarian = new Librarian(); 
            currentLibrarian.setId(1L);
            currentLibrarian.setName("Thủ thư hệ thống");
            
            Borrowing borrowing = borrowingService.createBorrowing(bookId, readerId, currentLibrarian);
            return "redirect:/borrowings?success=created";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("borrowing", new Borrowing());
            model.addAttribute("readers", readerService.getAllReaders());
            model.addAttribute("availableBooks", bookService.getAvailableBooks());
            return "borrowings/form";
        }
    }
    
    // Hiển thị chi tiết giao dịch
    @GetMapping("/{id}")
    public String showBorrowingDetail(@PathVariable Long id, Model model) {
        Optional<Borrowing> borrowing = borrowingService.getBorrowingById(id);
        if (borrowing.isPresent()) {
            model.addAttribute("borrowing", borrowing.get());
            return "borrowings/detail";
        }
        return "redirect:/borrowings?error=notfound";
    }
    
    // Xử lý trả sách
    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable Long id,
                           @RequestParam(defaultValue = "Tốt") String bookCondition) {
        try {
            // Tạm thời sử dụng librarian giả - trong thực tế sẽ lấy từ session
            Librarian currentLibrarian = new Librarian(); 
            currentLibrarian.setId(1L);
            currentLibrarian.setName("Thủ thư hệ thống");
            
            borrowingService.returnBook(id, bookCondition, currentLibrarian);
            return "redirect:/borrowings/" + id + "?success=returned";
        } catch (Exception e) {
            return "redirect:/borrowings/" + id + "?error=" + e.getMessage();
        }
    }
    
    // Xử lý gia hạn
    @PostMapping("/{id}/renew")
    public String renewBorrowing(@PathVariable Long id,
                               @RequestParam(defaultValue = "14") int additionalDays) {
        try {
            borrowingService.renewBorrowing(id, additionalDays);
            return "redirect:/borrowings/" + id + "?success=renewed";
        } catch (Exception e) {
            return "redirect:/borrowings/" + id + "?error=" + e.getMessage();
        }
    }
    
    // Hiển thị sách quá hạn
    @GetMapping("/overdue")
    public String showOverdueBorrowings(Model model) {
        List<Borrowing> overdueBorrowings = borrowingService.getOverdueBorrowings();
        model.addAttribute("borrowings", overdueBorrowings);
        model.addAttribute("pageTitle", "Sách Quá Hạn");
        return "borrowings/overdue";
    }
    
    // Hiển thị sách sắp hết hạn
    @GetMapping("/due-soon")
    public String showDueSoonBorrowings(@RequestParam(defaultValue = "7") int days, Model model) {
        List<Borrowing> dueSoonBorrowings = borrowingService.getBorrowingsDueSoon(days);
        model.addAttribute("borrowings", dueSoonBorrowings);
        model.addAttribute("days", days);
        model.addAttribute("pageTitle", "Sách Sắp Hết Hạn");
        return "borrowings/due-soon";
    }
    
    // REST API Endpoints
    @RestController
    @RequestMapping("/api/borrowings")
    public static class BorrowingRestController {
        
        @Autowired
        private BorrowingService borrowingService;
        
        @GetMapping
        public ResponseEntity<List<Borrowing>> getAllBorrowings() {
            return ResponseEntity.ok(borrowingService.getAllBorrowings());
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
            return borrowingService.getBorrowingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }
        
        @PostMapping
        public ResponseEntity<Borrowing> createBorrowing(@RequestParam Long bookId,
                                                       @RequestParam Long readerId) {
            try {
                // Tạm thời sử dụng librarian giả
                Librarian currentLibrarian = new Librarian(); 
                currentLibrarian.setId(1L);
                currentLibrarian.setName("Thủ thư hệ thống");
                
                Borrowing borrowing = borrowingService.createBorrowing(bookId, readerId, currentLibrarian);
                return ResponseEntity.ok(borrowing);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @PostMapping("/{id}/return")
        public ResponseEntity<Borrowing> returnBook(@PathVariable Long id,
                                                   @RequestParam(defaultValue = "Tốt") String bookCondition) {
            try {
                // Tạm thời sử dụng librarian giả
                Librarian currentLibrarian = new Librarian(); 
                currentLibrarian.setId(1L);
                currentLibrarian.setName("Thủ thư hệ thống");
                
                Borrowing borrowing = borrowingService.returnBook(id, bookCondition, currentLibrarian);
                return ResponseEntity.ok(borrowing);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @PostMapping("/{id}/renew")
        public ResponseEntity<Borrowing> renewBorrowing(@PathVariable Long id,
                                                       @RequestParam(defaultValue = "14") int additionalDays) {
            try {
                Borrowing borrowing = borrowingService.renewBorrowing(id, additionalDays);
                return ResponseEntity.ok(borrowing);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @GetMapping("/current")
        public ResponseEntity<List<Borrowing>> getCurrentBorrowings() {
            return ResponseEntity.ok(borrowingService.getCurrentBorrowings());
        }
        
        @GetMapping("/overdue")
        public ResponseEntity<List<Borrowing>> getOverdueBorrowings() {
            return ResponseEntity.ok(borrowingService.getOverdueBorrowings());
        }
        
        @GetMapping("/due-soon")
        public ResponseEntity<List<Borrowing>> getBorrowingsDueSoon(
                @RequestParam(defaultValue = "7") int days) {
            return ResponseEntity.ok(borrowingService.getBorrowingsDueSoon(days));
        }
        
        @GetMapping("/statistics")
        public ResponseEntity<Object> getBorrowingStatistics() {
            return ResponseEntity.ok(new Object() {
                public final Long totalBorrowings = borrowingService.getTotalBorrowingsCount();
                public final Long currentBorrowings = borrowingService.getCurrentBorrowingsCount();
                public final Long overdueBorrowings = borrowingService.getOverdueBorrowingsCount();
                public final Long returnedBorrowings = borrowingService.getReturnedBorrowingsCount();
            });
        }
        
        @GetMapping("/reader/{readerId}")
        public ResponseEntity<List<Borrowing>> getBorrowingsByReader(@PathVariable Long readerId) {
            try {
                Optional<Reader> reader = borrowingService.getReaderService().getReaderById(readerId);
                if (reader.isPresent()) {
                    List<Borrowing> borrowings = borrowingService.getBorrowingsByReader(reader.get());
                    return ResponseEntity.ok(borrowings);
                }
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @GetMapping("/book/{bookId}")
        public ResponseEntity<List<Borrowing>> getBorrowingsByBook(@PathVariable Long bookId) {
            try {
                Optional<Book> book = borrowingService.getBookService().getBookById(bookId);
                if (book.isPresent()) {
                    List<Borrowing> borrowings = borrowingService.getBorrowingsByBook(book.get());
                    return ResponseEntity.ok(borrowings);
                }
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }
}