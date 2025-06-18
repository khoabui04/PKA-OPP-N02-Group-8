package library;

import java.io.Serializable;
import java.util.Date;

public class BorrowingSlip implements Serializable {
    private String slipID;
    private Borrower borrower;
    private Book book;
    private Date borrowDate;
    private Date dueDate;

    public BorrowingSlip(String slipID, Borrower borrower, Book book, Date borrowDate, Date dueDate) {
        this.slipID = slipID;
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getSlipID() { return slipID; }
    public Borrower getBorrower() { return borrower; }
    public Book getBook() { return book; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }

    // Phương thức thêm mới có khối try-catch-finally
    public void processBorrowing() {
        try {
            System.out.println("Processing borrowing slip...");

            if (book == null) {
                throw new Exception("Book is null");
            }

            if (borrower == null) {
                throw new Exception("Borrower is null");
            }

            // Nếu mọi thứ hợp lệ, in ra thông tin phiếu mượn
            System.out.println(this);

        } catch (Exception e) {
            System.err.println("Error while processing borrowing slip: " + e.getMessage());
        } finally {
            System.out.println("Finished processing borrowing slip: " + slipID);
        }
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
}
