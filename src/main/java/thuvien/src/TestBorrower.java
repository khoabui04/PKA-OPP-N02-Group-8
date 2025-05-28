<<<<<<< HEAD
package thuvien;
=======
package thuvien.src;

import thuvien.Book;
import thuvien.Borrower;

>>>>>>> bba4ff6c75c190cf0ecfa8a8199c5c4a9141b900

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