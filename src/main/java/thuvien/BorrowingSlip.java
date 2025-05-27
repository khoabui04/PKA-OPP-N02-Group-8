package thuvien;

import java.util.Date;
import java.util.Objects;

public class BorrowingSlip {
    private final String slipID;
    private final Borrower borrower;
    private final Book book;
    private final Date borrowDate;
    private final Date dueDate;

    public BorrowingSlip(String slipID, Borrower borrower, Book book, Date borrowDate, Date dueDate) {
        this.slipID = slipID;
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = new Date(borrowDate.getTime());
        this.dueDate = new Date(dueDate.getTime());
    }

    public String getSlipID() {
        return slipID;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowDate() {
        return new Date(borrowDate.getTime());
    }

    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    @Override
    public String toString() {
        return "BorrowingSlip{" +
                "slipID='" + slipID + '\'' +
                ", borrower=" + borrower +
                ", book=" + book +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowingSlip)) return false;
        BorrowingSlip that = (BorrowingSlip) o;
        return Objects.equals(slipID, that.slipID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slipID);
    }
}
