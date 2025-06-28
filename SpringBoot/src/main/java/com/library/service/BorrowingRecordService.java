package com.library.service;

import com.library.model.Book;
import com.library.model.BorrowingRecord;
import com.library.repository.BookRepository;
import com.library.repository.BorrowingRecordRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowingRecordService {
    private final BorrowingRecordRepository recordRepository;
    private final BookRepository bookRepository;

    public BorrowingRecordService() {
        this.recordRepository = new BorrowingRecordRepository();
        this.bookRepository = new BookRepository();
    }

    public void addBorrowingRecord(BorrowingRecord record) {
        try {
            Book book = bookRepository.findById(record.getBookId());
            if (book != null && book.isAvailable()) {
                book.setAvailable(false);
                bookRepository.updateBook(book);
                recordRepository.create(record);
            } else {
                throw new IllegalStateException("Sách không có sẵn để mượn.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm bản ghi mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác thêm bản ghi mượn.");
        }
    }

    public List<Book> listBorrowedBooks(String borrowerId) {
        try {
            List<BorrowingRecord> records = recordRepository.readAll().stream()
                    .filter(record -> record.getBorrowerId().equals(borrowerId))
                    .collect(Collectors.toList());
            return records.stream()
                    .map(record -> bookRepository.findById(record.getBookId()))
                    .filter(book -> book != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Lỗi khi liệt kê sách đã mượn: " + e.getMessage());
            return List.of();
        } finally {
            System.out.println("Hoàn thành thao tác liệt kê sách đã mượn.");
        }
    }

    public List<Book> listBooksNearDueDate(String borrowerId) {
        try {
            LocalDate today = LocalDate.now();
            List<BorrowingRecord> records = recordRepository.readAll().stream()
                    .filter(record -> record.getBorrowerId().equals(borrowerId))
                    .filter(record -> record.getDueDate().isBefore(today.plusDays(4)))
                    .collect(Collectors.toList());
            return records.stream()
                    .map(record -> bookRepository.findById(record.getBookId()))
                    .filter(book -> book != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Lỗi khi liệt kê sách sắp đến hạn: " + e.getMessage());
            return List.of();
        } finally {
            System.out.println("Hoàn thành thao tác liệt kê sách sắp đến hạn.");
        }
    }

    public BorrowingRecordRepository getRecordRepository() {
        return recordRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}