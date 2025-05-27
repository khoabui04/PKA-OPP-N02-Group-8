package thuvien.src;

import thuvien.Book;
import thuvien.Borrower;


public class TestBorrower {
    public static void main(String[] args) {
        Borrower borrower = new Borrower("B001", "Nguyen Van A");
        Book book1 = new Book("001", "KhoaPug", "Huy");
        Book book2 = new Book("002", "Clean Code", "Robert C. Martin");

        borrower.borrowBook(book1);
        borrower.borrowBook(book2);

        System.out.println("Borrower Name: " + borrower.getBorrowerName());
        System.out.println("Books Borrowed:");
        for (Book b : borrower.getBorrowedBooks()) {
            System.out.println("- " + b.getBookName() + " by " + b.getAuthor());
        }
    }
}
