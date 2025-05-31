// BorrowingSlipManager.java
public static void createSlip() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập mã phiếu mượn: ");
    String slipID = scanner.nextLine();

    System.out.print("Nhập mã người mượn: ");
    String borrowerID = scanner.nextLine();
    System.out.print("Nhập tên người mượn: ");
    String borrowerName = scanner.nextLine();
    System.out.print("Nhập số điện thoại: ");
    String phone = scanner.nextLine();
    Borrower borrower = new Borrower(borrowerID, borrowerName, phone);

    System.out.print("Nhập mã sách: ");
    String bookID = scanner.nextLine();
    System.out.print("Nhập tên sách: ");
    String bookName = scanner.nextLine();
    System.out.print("Nhập tác giả: ");
    String author = scanner.nextLine();
    Book book = new Book(bookID, bookName, author);

    Date borrowDate = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(borrowDate);
    cal.add(Calendar.DAY_OF_MONTH, 7);
    Date dueDate = cal.getTime();

    BorrowingSlip slip = new BorrowingSlip(slipID, borrower, book, borrowDate, dueDate);
    crud.add(slip);
    System.out.println("✅ Thêm phiếu mượn thành công.");
}