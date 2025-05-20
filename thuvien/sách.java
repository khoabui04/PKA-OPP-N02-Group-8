// Lớp Book mô tả một quyển sách trong thư viện
public class sách {
    private String title;       // Tên sách
    private boolean available;  // Trạng thái: còn sẵn sàng cho mượn hay không

    // Hàm khởi tạo sách với tiêu đề
    public sách(String title) {
        this.title = title;
        this.available = true; // Mặc định là có sẵn
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Đánh dấu sách đã trả (trở về thư viện)
    public void checkIn() {
        this.available = true;
    }

    // Đánh dấu sách đang được mượn
    public void checkOut() {
        this.available = false;
    }

    // Hàm main để test lớp Book
    public static void main(String[] args) {
        sách book = new sách("Lập trình Java");
        System.out.println("Tên sách: " + book.getTitle());
        System.out.println("Có sẵn? " + book.isAvailable());

        book.checkOut();
        System.out.println("Sau khi mượn, có sẵn? " + book.isAvailable());

        book.checkIn();
        System.out.println("Sau khi trả, có sẵn? " + book.isAvailable());
    }
}

