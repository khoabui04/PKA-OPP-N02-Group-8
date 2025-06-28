package com.library.model;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowingRecord implements Serializable {
    private String recordId;
    private String bookId;
    private String borrowerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned = false;

    public BorrowingRecord(String recordId, String bookId, String borrowerId, LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getBorrowerId() { return borrowerId; }
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{ID=" + recordId + ", BookID=" + bookId + ", BorrowerID=" + borrowerId +
               ", BorrowDate=" + borrowDate + ", DueDate=" + dueDate + "}";
    }
}