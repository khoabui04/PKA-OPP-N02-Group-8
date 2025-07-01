package library;

public class TestBook {
    public static void main(String[] args) {
        try {
            Book book = new Book("001", "KhoaPug", "Huy");
            System.out.println("Book ID: " + book.getBookID());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author: " + book.getAuthor());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc kiểm thử Book.");
        }
    }
}