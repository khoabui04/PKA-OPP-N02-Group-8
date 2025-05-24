import java.util.*;

public class Borrower {
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

    public Borrower() {}

    public Borrower(String borrowerID, String name, String contactInfo, String address,
                    Date dateOfBirth, Date membershipDate, List<String> currentBorrowings,
                    List<String> borrowingHistory, double fineAmount, String status) {
        this.borrowerID = borrowerID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.membershipDate = membershipDate;
        this.currentBorrowings = currentBorrowings;
        this.borrowingHistory = borrowingHistory;
        this.fineAmount = fineAmount;
        this.status = status;
    }

    public void printInfo() {
        System.out.println("Borrower ID: " + borrowerID);
        System.out.println("Name: " + name);
        System.out.println("Contact Info: " + contactInfo);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Membership Date: " + membershipDate);
        System.out.println("Current Borrowings: " + currentBorrowings);
        System.out.println("Borrowing History: " + borrowingHistory);
        System.out.println("Fine Amount: " + fineAmount + " VND");
        System.out.println("Status: " + status);
    }

    public static void main(String[] args) {
        List<String> currentBooks = Arrays.asList("Java for Beginners", "Effective Java");
        List<String> historyBooks = Arrays.asList("Clean Code", "Design Patterns");

        Borrower b = new Borrower(
            "B001",
            "Van Khanh",
            "0123456789",
            "Cau Giay, Ha Noi",
            new GregorianCalendar(2002, Calendar.MARCH, 6).getTime(),
            new Date(),
            currentBooks,
            historyBooks,
            50000.0,
            "Active"
        );

        b.printInfo();
    }
}
