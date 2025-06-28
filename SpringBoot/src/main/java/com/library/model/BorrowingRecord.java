package com.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord implements Serializable {
    @Id
    private String recordId;
    private String bookId;
    private String borrowerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingRecord() {}
    public BorrowingRecord(String recordId, String bookId, String borrowerId, LocalDate borrowDate, LocalDate returnDate) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getBorrowerId() { return borrowerId; }
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "BorrowingRecord{ID=" + recordId + ", BookID=" + bookId + ", BorrowerID=" + borrowerId +
               ", BorrowDate=" + borrowDate + ", ReturnDate=" + returnDate + "}";
    }
}