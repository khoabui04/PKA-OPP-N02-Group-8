// BookManager.java
public static void filterBooksByAuthor() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập tên tác giả cần lọc: ");
    String author = scanner.nextLine();
    boolean found = false;
    for (Book book : crud.getAll()) {
        if (book.getAuthor().equalsIgnoreCase(author)) {
            System.out.println(book);
            found = true;
        }
    }
    if (!found) {
        System.out.println("❌ Không tìm thấy sách của tác giả này.");
    }
}