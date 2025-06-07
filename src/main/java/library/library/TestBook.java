package library;

public class TestBook {
    public static void main(String[] args) {
        Book book = new Book("001", "KhoaPug", "Huy");
        System.out.println("Book ID: " + book.getBookID());
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Author: " + book.getAuthor());
    }
}