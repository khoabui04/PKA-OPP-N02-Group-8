

public class BorrowingSlip {
    private String slipID;
    private Book book;
    private Borrower borrower;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;
    private int renewalCount;
    private String status; // active, completed, overdue
    private String staffID;
    private String damageNotes;
    private static final double FINE_PER_DAY = 5000.0; // 5,000 VND per day
    private static final int MAX_RENEWALS = 2;

    /**
     * Constructor tạo phiếu mượn mới
     * 
     * @param slipID      Mã phiếu mượn
     * @param book        Sách được mượn
     * @param borrower    Người mượn
     * @param borrowDate  Ngày mượn
     * @param borrowDays  Số ngày được mượn
     * @param staffID     Mã nhân viên thực hiện
     */
    public BorrowingSlip(String slipID, Book book, Borrower borrower, 
                         Date borrowDate, int borrowDays, String staffID) {
        this.slipID = slipID;
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        
        // Tính ngày đến hạn
        this.dueDate = new Date(borrowDate.getTime() + TimeUnit.DAYS.toMillis(borrowDays));
        
        this.returnDate = null; // Chưa trả
        this.fine = 0.0;
        this.renewalCount = 0;
        this.status = "active";
        this.staffID = staffID;
        this.damageNotes = "";
        
        // Thêm vào lịch sử mượn của người mượn
        borrower.getBorrowingHistory().add(this);
    }

    // Getters and Setters
    public String getSlipID() {
        return slipID;
    }

    public void setSlipID(String slipID) {
        this.slipID = slipID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        if (returnDate != null) {
            calculateFine();
        }
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDamageNotes() {
        return damageNotes;
    }

    public void setDamageNotes(String damageNotes) {
        this.damageNotes = damageNotes;
    }

    // Business methods
    /**
     * Tính tiền phạt dựa trên ngày trả và ngày đến hạn
     * 
     * @return Số tiền phạt
     */
    public double calculateFine() {
        if (returnDate == null || !isOverdue()) {
            fine = 0.0;
            return fine;
        }
        
        long diffInMillies = returnDate.getTime() - dueDate.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        fine = diffInDays * FINE_PER_DAY;
        
        // Cập nhật tiền phạt cho người mượn
        borrower.setFineAmount(borrower.getFineAmount() + fine);
        
        return fine;
    }

    /**
     * Kiểm tra phiếu mượn có quá hạn không
     * 
     * @return true nếu phiếu mượn quá hạn
     */
    public boolean isOverdue() {
        if (returnDate == null) {
            // Nếu chưa trả, so sánh với ngày hiện tại
            Date now = new Date();
            return now.after(dueDate);
        } else {
            // Nếu đã trả, so sánh ngày trả với ngày đến hạn
            return returnDate.after(dueDate);
        }
    }

    /**
     * Hoàn thành giao dịch trả sách
     * 
     * @param returnDate Ngày trả sách
     * @return true nếu trả sách thành công
     */
    public boolean completeTransaction(Date returnDate) {
        if (status.equals("completed")) {
            System.out.println("Phiếu mượn đã được hoàn thành trước đó.");
            return false;
        }
        
        this.returnDate = returnDate;
        this.book.checkIn();
        this.borrower.returnBook(book);
        calculateFine();
        
        this.status = "completed";
        
        System.out.println("Giao dịch hoàn thành. Sách trả ngày: " + returnDate);
        if (fine > 0) {
            System.out.println("Tiền phạt: " + fine + " VND");
        }
        
        return true;
    }

    /**
     * Gia hạn thời gian mượn sách
     * 
     * @param days Số ngày gia hạn
     * @return true nếu gia hạn thành công
     */
    public boolean extendDueDate(int days) {
        if (isOverdue()) {
            System.out.println("Không thể gia hạn cho sách đã quá hạn.");
            return false;
        }
        
        if (renewalCount >= MAX_RENEWALS) {
            System.out.println("Đã đạt số lần gia hạn tối đa.");
            return false;
        }
        
        // Thêm ngày vào ngày đến hạn hiện tại
        Date newDueDate = new Date(dueDate.getTime() + TimeUnit.DAYS.toMillis(days));
        this.dueDate = newDueDate;
        this.renewalCount++;
        
        System.out.println("Đã gia hạn thêm " + days + " ngày. Hạn mới: " + dueDate);
        return true;
    }

    /**
     * Tạo biên lai cho người mượn
     * 
     * @return Chuỗi biên lai
     */
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("===== BIÊN LAI MƯỢN SÁCH =====\n");
        receipt.append("Mã phiếu: ").append(slipID).append("\n");
        receipt.append("Người mượn: ").append(borrower.getName()).append("\n");
        receipt.append("Sách: ").append(book.getTitle()).append("\n");
        receipt.append("Ngày mượn: ").append(borrowDate).append("\n");
        receipt.append("Ngày đến hạn: ").append(dueDate).append("\n");
        
        if (returnDate != null) {
            receipt.append("Ngày trả: ").append(returnDate).append("\n");
            receipt.append("Tiền phạt: ").append(fine).append(" VND\n");
        }
        
        receipt.append("Nhân viên: ").append(staffID).append("\n");
        receipt.append("================================");
        
        return receipt.toString();
    }

    /**
     * Hủy phiếu mượn (chỉ áp dụng khi chưa mượn thực tế)
     * 
     * @return true nếu hủy thành công
     */
    public boolean cancelBorrowing() {
        if (!status.equals("active")) {
            System.out.println("Không thể hủy phiếu mượn ở trạng thái " + status);
            return false;
        }
        
        // Đặt lại sách thành có sẵn
        book.setAvailable(true);
        status = "cancelled";
        
        // Xóa khỏi danh sách đang mượn
        borrower.getCurrentBorrowings().remove(book);
        
        System.out.println("Đã hủy phiếu mượn " + slipID);
        return true;
    }

    /**
     * Ghi lại tình trạng hư hỏng của sách khi trả
     * 
     * @param description Mô tả tình trạng hư hỏng
     */
    public void recordDamage(String description) {
        this.damageNotes