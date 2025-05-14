public // Book.java
package com.library.model;

import java.util.Objects;

/**
 * Lớp Book đại diện cho một cuốn sách trong thư viện
 */
public class Book {
    private String bookID;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private String genre;
    private boolean available;
    private String location;
    private String isbn;
    private int pageCount;
    private String condition; // new, good, fair, poor

    /**
     * Constructor tạo một đối tượng Book mới
     * 
     * @param bookID      Mã định danh duy nhất của sách
     * @param title       Tên sách
     * @param author      Tác giả
     * @param publisher   Nhà xuất bản
     * @param publishYear Năm xuất bản
     * @param genre       Thể loại
     * @param isbn        Mã số ISBN
     * @param pageCount   Số trang
     * @param location    Vị trí trong thư viện
     */
    public Book(String bookID, String title, String author, String publisher, int publishYear, 
                String genre, String isbn, int pageCount, String location) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.genre = genre;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.location = location;
        this.available = true;
        this.condition = "new";
    }

    // Getters and Setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Business methods
    /**
     * Đánh dấu sách đã được mượn
     * 
     * @return true nếu thao tác thành công
     */
    public boolean checkOut() {
        if (available) {
            available = false;
            System.out.println("Sách '" + title + "' đã được mượn.");
            return true;
        } else {
            System.out.println("Sách '" + title + "' không khả dụng để mượn.");
            return false;
        }
    }

    /**
     * Đánh dấu sách đã được trả
     * 
     * @return true nếu thao tác thành công
     */
    public boolean checkIn() {
        if (!available) {
            available = true;
            System.out.println("Sách '" + title + "' đã được trả.");
            return true;
        } else {
            System.out.println("Sách '" + title + "' đã có sẵn trong thư viện.");
            return false;
        }
    }

    /**
     * Cập nhật vị trí mới cho sách
     * 
     * @param newLocation Vị trí mới trong thư viện
     */
    public void updateLocation(String newLocation) {
        this.location = newLocation;
        System.out.println("Vị trí của sách '" + title + "' đã được cập nhật thành " + newLocation);
    }

    /**
     * Hiển thị thông tin chi tiết của sách
     */
    public void displayInfo() {
        System.out.println("Thông tin sách:");
        System.out.println("ID: " + bookID);
        System.out.println("Tên: " + title);
        System.out.println("Tác giả: " + author);
        System.out.println("Nhà xuất bản: " + publisher);
        System.out.println("Năm xuất bản: " + publishYear);
        System.out.println("Thể loại: " + genre);
        System.out.println("ISBN: " + isbn);
        System.out.println("Số trang: " + pageCount);
        System.out.println("Khả dụng: " + (available ? "Có" : "Không"));
        System.out.println("Vị trí: " + location);
        System.out.println("Tình trạng: " + condition);
    }

    /**
     * Trả về trạng thái hiện tại của sách
     * 
     * @return Chuỗi mô tả trạng thái sách
     */
    public String getBookStatus() {
        String status = available ? "Khả dụng" : "Đã được mượn";
        return "Sách '" + title + "' - " + status + " - Tình trạng: " + condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookID.equals(book.bookID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}

// Borrower.java
package com.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Lớp Borrower đại diện cho một người mượn trong thư viện
 */
public class Borrower {
    private String borrowerID;
    private String name;
    private String contactInfo;
    private String address;
    private Date dateOfBirth;
    private Date membershipDate;
    private List<Book> currentBorrowings;
    private List<BorrowingSlip> borrowingHistory;
    private double fineAmount;
    private String status; // active, suspended, expired

    /**
     * Constructor tạo một đối tượng Borrower mới
     * 
     * @param borrowerID  Mã định danh của người mượn
     * @param name        Họ tên đầy đủ
     * @param contactInfo Thông tin liên hệ
     * @param address     Địa chỉ
     * @param dateOfBirth Ngày sinh
     */
    public Borrower(String borrowerID, String name, String contactInfo, 
                    String address, Date dateOfBirth) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.membershipDate = new Date(); // Ngày hiện tại
        this.currentBorrowings = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
        this.fineAmount = 0.0;
        this.status = "active";
    }

    // Getters and Setters
    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public List<Book> getCurrentBorrowings() {
        return currentBorrowings;
    }

    public void setCurrentBorrowings(List<Book> currentBorrowings) {
        this.currentBorrowings = currentBorrowings;
    }

    public List<BorrowingSlip> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(List<BorrowingSlip> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Business methods
    /**
     * Mượn một cuốn sách
     * 
     * @param book Cuốn sách cần mượn
     * @return true nếu mượn thành công
     */
    public boolean borrowBook(Book book) {
        if (!checkEligibility()) {
            System.out.println("Người mượn không đủ điều kiện để mượn sách.");
            return false;
        }
        
        if (book.isAvailable()) {
            currentBorrowings.add(book);
            book.checkOut();
            System.out.println(name + " đã mượn sách: " + book.getTitle());
            return true;
        } else {
            System.out.println("Sách không khả dụng để mượn.");
            return false;
        }
    }

    /**
     * Trả một cuốn sách
     * 
     * @param book Cuốn sách cần trả
     * @return true nếu trả thành công
     */
    public boolean returnBook(Book book) {
        if (currentBorrowings.contains(book)) {
            currentBorrowings.remove(book);
            book.checkIn();
            System.out.println(name + " đã trả sách: " + book.getTitle());
            return true;
        } else {
            System.out.println(name + " không mượn sách này: " + book.getTitle());
            return false;
        }
    }

    /**
     * Thanh toán tiền phạt
     * 
     * @param amount Số tiền thanh toán
     * @return true nếu thanh toán thành công
     */
    public boolean payFine(double amount) {
        if (amount <= 0 || amount > fineAmount) {
            System.out.println("Số tiền thanh toán không hợp lệ");
            return false;
        }
        
        fineAmount -= amount;
        System.out.println(name + " đã thanh toán " + amount + ". Tiền phạt còn lại: " + fineAmount);
        return true;
    }

    /**
     * Cập nhật thông tin liên hệ
     * 
     * @param newContactInfo Thông tin liên hệ mới
     */
    public void updateContactInfo(String newContactInfo) {
        this.contactInfo = newContactInfo;
        System.out.println("Thông tin liên hệ đã được cập nhật: " + newContactInfo);
    }

    /**
     * Kiểm tra khả năng mượn sách
     * 
     * @return true nếu đủ điều kiện mượn sách
     */
    public boolean checkEligibility() {
        if (!status.equals("active")) {
            System.out.println("Tài khoản người mượn không hoạt động.");
            return false;
        }
        
        if (fineAmount > 50.0) {
            System.out.println("Tiền phạt chưa thanh toán quá cao.");
            return false;
        }
        
        if (currentBorrowings.size() >= 5) {
            System.out.println("Đã đạt số lượng sách mượn tối đa.");
            return false;
        }
        
        return true;
    }

    /**
     * Gia hạn thời gian thành viên
     * 
     * @param months Số tháng cần gia hạn
     */
    public void extendMembership(int months) {
        if (months <= 0) {
            System.out.println("Số tháng gia hạn không hợp lệ");
            return;
        }
        
        // Cập nhật status nếu hết hạn
        if (status.equals("expired")) {
            status = "active";
        }
        
        System.out.println("Gia hạn thành viên " + months + " tháng cho " + name);
    }

    /**
     * Tính tổng tiền phạt
     * 
     * @return Tổng tiền phạt
     */
    public double calculateTotalFines() {
        // Trong trường hợp thực tế, có thể cập nhật từ các phiếu mượn
        return fineAmount;
    }

    /**
     * Hiển thị sách đang mượn
     */
    public void displayCurrentBorrowings() {
        if (currentBorrowings.isEmpty()) {
            System.out.println(name + " không có sách nào đang mượn.");
            return;
        }
        
        System.out.println("Sách đang mượn của " + name + ":");
        for (Book book : currentBorrowings) {
            System.out.println("- " + book.getTitle() + " bởi " + book.getAuthor());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return borrowerID.equals(borrower.borrowerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerID);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerID='" + borrowerID + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", borrowedBooks=" + currentBorrowings.size() +
                ", status='" + status + '\'' +
                '}';
    }
}

// BorrowingSlip.java
package com.library.model;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Lớp BorrowingSlip đại diện cho một phiếu mượn sách
 */
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