package thuvien;

public class TestBorrower {
    public static void main(String[] args) {
        Borrower borrower = new Borrower("B001", "Nguyen Van A", "0123456789");
        Book book1 = new Book("001", "KhoaPug", "Huy");
        Book book2 = new Book("002", "Clean Code", "Robert C. Martin");

        // Nếu Borrower chưa có các phương thức này, hãy bổ sung ở dưới
        // borrower.borrowBook(book1);
        // borrower.borrowBook(book2);

        System.out.println("Borrower ID: " + borrower.getBorrowerID());
        System.out.println("Borrower: " + borrower);
    }
}