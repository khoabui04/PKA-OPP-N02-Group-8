package library;

public class TestBorrower {
    public static void main(String[] args) {
        try {
            Borrower borrower = new Borrower("B001", "Nguyen Van A", "0123456789");
            Book book1 = new Book("001", "KhoaPug", "Huy");
            Book book2 = new Book("002", "Clean Code", "Robert C. Martin");

            borrower.borrowBook(book1);
            borrower.borrowBook(book2);

            System.out.println("Borrower ID: " + borrower.getBorrowerID());
            System.out.println("Borrower: " + borrower);
            System.out.println("Books Borrowed:");
            for (Book b : borrower.getBorrowedBooks()) {
                System.out.println("- " + b.getBookName() + " by " + b.getAuthor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc kiểm thử Borrower.");
        }
    }
}