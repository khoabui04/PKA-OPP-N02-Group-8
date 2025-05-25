import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class BorrowerManager {
    private String borrowerID;
    private String name;
    private String contactInfo;
    private String address;
    private Date dateOfBirth;
    private Date membershipDate;
    private List<String> currentBorrowings;
    private List<String> borrowingHistory;
    private double fineAmount;
    private String status;

    public BorrowerManager(String borrowerID, String name, String contactInfo, String address, 
                    Date dateOfBirth, Date membershipDate, String status) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.membershipDate = membershipDate;
        this.currentBorrowings = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
        this.fineAmount = 0.0;
        this.status = status;
    }

    public void addCurrentBorrowing(String slipID) {
        currentBorrowings.add(slipID);
    }

    public void returnBorrowing(String slipID) {
        currentBorrowings.remove(slipID);
        borrowingHistory.add(slipID);
    }

    @Override
    public String toString() { 
        return "BorrowerManager{" +
                "ID='" + borrowerID + '\'' +
                ", Name='" + name + '\'' +
                ", Contact='" + contactInfo + '\'' +
                ", Address='" + address + '\'' +
                ", DOB=" + dateOfBirth +
                ", MembershipDate=" + membershipDate +
                ", CurrentBorrowings=" + currentBorrowings +
                ", BorrowingHistory=" + borrowingHistory +
                ", Fine=" + fineAmount +
                ", Status='" + status + '\'' +
                '}';
    }
}