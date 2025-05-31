// BorrowerManager.java
public static void searchBorrowerByName() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập tên người mượn cần tìm: ");
    String name = scanner.nextLine();
    boolean found = false;
    for (Borrower b : crud.getAll()) {
        if (b.getBorrowerName().toLowerCase().contains(name.toLowerCase())) {
            System.out.println(b);
            found = true;
        }
    }
    if (!found) {
        System.out.println("❌ Không tìm thấy người mượn này.");
    }
}