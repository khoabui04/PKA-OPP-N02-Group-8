package src.thuvien;

public class Borrower {
    private String borrowerID;
    private String borrowerName;
    private String phoneNumber;

    public Borrower(String borrowerID, String borrowerName, String phoneNumber) {
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.phoneNumber = phoneNumber;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Mã: " + borrowerID + ", Tên: " + borrowerName + ", SĐT: " + phoneNumber;
    }
}
