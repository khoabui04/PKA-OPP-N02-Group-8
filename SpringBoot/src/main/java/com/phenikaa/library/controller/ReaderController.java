package com.phenikaa.library.controller;

import com.phenikaa.library.model.Reader;
import com.phenikaa.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/readers")
public class ReaderController {
    
    @Autowired
    private ReaderService readerService;
    
    // Hiển thị danh sách độc giả
    @GetMapping
    public String listReaders(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "name") String sortBy,
                             @RequestParam(defaultValue = "asc") String sortDir,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String readerType,
                             @RequestParam(required = false) String status,
                             Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Reader.ReaderType type = null;
        Reader.ReaderStatus readerStatus = null;
        
        try {
            if (readerType != null && !readerType.trim().isEmpty()) {
                type = Reader.ReaderType.valueOf(readerType);
            }
            if (status != null && !status.trim().isEmpty()) {
                readerStatus = Reader.ReaderStatus.valueOf(status);
            }
        } catch (IllegalArgumentException e) {
            // Invalid enum values, ignore
        }
        
        Page<Reader> readerPage = readerService.searchReaders(search, null, type, readerStatus, pageable);
        
        model.addAttribute("readers", readerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", readerPage.getTotalPages());
        model.addAttribute("totalElements", readerPage.getTotalElements());
        model.addAttribute("readerTypes", Reader.ReaderType.values());
        model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
        model.addAttribute("currentSearch", search);
        model.addAttribute("currentReaderType", readerType);
        model.addAttribute("currentStatus", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "readers/list";
    }
    
    // Hiển thị form thêm độc giả mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reader", new Reader());
        model.addAttribute("readerTypes", Reader.ReaderType.values());
        model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
        return "readers/form";
    }
    
    // Xử lý thêm độc giả mới
    @PostMapping
    public String createReader(@Valid @ModelAttribute Reader reader, 
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("readerTypes", Reader.ReaderType.values());
            model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
            return "readers/form";
        }
        
        try {
            readerService.saveReader(reader);
            return "redirect:/readers?success=created";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("readerTypes", Reader.ReaderType.values());
            model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
            return "readers/form";
        }
    }
    
    // Hiển thị chi tiết độc giả
    @GetMapping("/{id}")
    public String showReaderDetail(@PathVariable Long id, Model model) {
        Optional<Reader> reader = readerService.getReaderById(id);
        if (reader.isPresent()) {
            model.addAttribute("reader", reader.get());
            return "readers/detail";
        }
        return "redirect:/readers?error=notfound";
    }
    
    // Hiển thị form chỉnh sửa độc giả
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Reader> reader = readerService.getReaderById(id);
        if (reader.isPresent()) {
            model.addAttribute("reader", reader.get());
            model.addAttribute("readerTypes", Reader.ReaderType.values());
            model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
            return "readers/form";
        }
        return "redirect:/readers?error=notfound";
    }
    
    // Xử lý cập nhật độc giả
    @PostMapping("/{id}")
    public String updateReader(@PathVariable Long id, 
                              @Valid @ModelAttribute Reader reader,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            reader.setId(id);
            model.addAttribute("readerTypes", Reader.ReaderType.values());
            model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
            return "readers/form";
        }
        
        try {
            readerService.updateReader(id, reader);
            return "redirect:/readers/" + id + "?success=updated";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            reader.setId(id);
            model.addAttribute("readerTypes", Reader.ReaderType.values());
            model.addAttribute("readerStatuses", Reader.ReaderStatus.values());
            return "readers/form";
        }
    }
    
    // Xóa độc giả
    @PostMapping("/{id}/delete")
    public String deleteReader(@PathVariable Long id) {
        try {
            readerService.deleteReader(id);
            return "redirect:/readers?success=deleted";
        } catch (Exception e) {
            return "redirect:/readers?error=" + e.getMessage();
        }
    }
    
    // Gia hạn thẻ độc giả
    @PostMapping("/{id}/renew")
    public String renewMembership(@PathVariable Long id, 
                                 @RequestParam(defaultValue = "12") int months) {
        try {
            readerService.renewMembership(id, months);
            return "redirect:/readers/" + id + "?success=renewed";
        } catch (Exception e) {
            return "redirect:/readers/" + id + "?error=" + e.getMessage();
        }
    }
    
    // REST API Endpoints
    @RestController
    @RequestMapping("/api/readers")
    public static class ReaderRestController {
        
        @Autowired
        private ReaderService readerService;
        
        @GetMapping
        public ResponseEntity<List<Reader>> getAllReaders() {
            return ResponseEntity.ok(readerService.getAllReaders());
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Reader> getReaderById(@PathVariable Long id) {
            return readerService.getReaderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }
        
        @PostMapping
        public ResponseEntity<Reader> createReader(@Valid @RequestBody Reader reader) {
            try {
                Reader savedReader = readerService.saveReader(reader);
                return ResponseEntity.ok(savedReader);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<Reader> updateReader(@PathVariable Long id, 
                                                  @Valid @RequestBody Reader reader) {
            try {
                Reader updatedReader = readerService.updateReader(id, reader);
                return ResponseEntity.ok(updatedReader);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
            try {
                readerService.deleteReader(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        
        @GetMapping("/search")
        public ResponseEntity<Page<Reader>> searchReaders(
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String email,
                @RequestParam(required = false) String readerType,
                @RequestParam(required = false) String status,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size) {
            
            Reader.ReaderType type = null;
            Reader.ReaderStatus readerStatus = null;
            
            try {
                if (readerType != null && !readerType.trim().isEmpty()) {
                    type = Reader.ReaderType.valueOf(readerType);
                }
                if (status != null && !status.trim().isEmpty()) {
                    readerStatus = Reader.ReaderStatus.valueOf(status);
                }
            } catch (IllegalArgumentException e) {
                // Invalid enum values, ignore
            }
            
            Pageable pageable = PageRequest.of(page, size);
            Page<Reader> readers = readerService.searchReaders(name, email, type, readerStatus, pageable);
            return ResponseEntity.ok(readers);
        }
        
        @GetMapping("/expired")
        public ResponseEntity<List<Reader>> getExpiredReaders() {
            return ResponseEntity.ok(readerService.getExpiredReaders());
        }
        
        @GetMapping("/expiring-soon")
        public ResponseEntity<List<Reader>> getReadersExpiringSoon(
                @RequestParam(defaultValue = "30") int days) {
            return ResponseEntity.ok(readerService.getReadersExpiringSoon(days));
        }
        
        @GetMapping("/statistics")
        public ResponseEntity<Object> getReaderStatistics() {
            return ResponseEntity.ok(new Object() {
                public final Long totalReaders = readerService.getTotalReadersCount();
                public final Long activeReaders = readerService.getActiveReadersCount();
                public final Long readersWithBorrowedBooks = readerService.getReadersWithBorrowedBooksCount();
            });
        }
        
        @PostMapping("/{id}/renew")
        public ResponseEntity<Void> renewMembership(@PathVariable Long id, 
                                                   @RequestParam(defaultValue = "12") int months) {
            try {
                readerService.renewMembership(id, months);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
    }
}