# PKA-OPP-N02-Group-8
OOP-Group-8
#1. Thành viên 
Nguyễn Thị Minh Hằng  Bùi Văn Khoa  Đỗ Vân Khánh 

#. Tiêu đề
Quản lý thư viện

#2. Đối Tượng 
-Book
-Người mượn 
-Phiếu mượn 

#3. Class 

class Book
  String bookID;
  String title;
  String author;
  String publisher;
  int publishYear;
  String genre;
  boolean available;
  String location;
  String isbn;
  int pageCount;
  String condition;
  
class Borrower 
   String borrowerID;
   String name;
   String contactInfo;
   String address;
   Date dateOfBirth;
   Date membershipDate;
   List<Book> currentBorrowings;
   List<BorrowingSlip> borrowingHistory;
   double fineAmount;
   String status;

class BorrowingSlip
    String slipID;
    Book book;
    Borrower borrower;
    Date borrowDate;
    Date dueDate;
    Date returnDate;
    double fine;
    int renewalCount;
    String status; 
    String staffID;
    String damageNotes;

#4. Cấu trúc thư mục Project
LibraryManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── library/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Book.java
│   │   │   │   │   │   ├── Borrower.java
│   │   │   │   │   │   └── BorrowingSlip.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── BookService.java
│   │   │   │   │   │   ├── BorrowerService.java
│   │   │   │   │   │   └── BorrowingService.java
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── BookRepository.java
│   │   │   │   │   │   ├── BorrowerRepository.java
│   │   │   │   │   │   └── BorrowingSlipRepository.java
│   │   │   │   │   ├── util/
│   │   │   │   │   │   ├── DateUtil.java
│   │   │   │   │   │   └── IDGenerator.java
│   │   │   │   │   └── exception/
│   │   │   │   │       ├── BookNotFoundException.java
│   │   │   │   │       ├── BorrowerNotFoundException.java
│   │   │   │   │       └── InvalidOperationException.java
│   │   │   │   └── Main.java
│   │   ├── resources/
│   │   │   └── config.properties
│   └── test/
│       └── java/
│           └── com/
│               └── library/
│                   ├── model/
│                   │   ├── BookTest.java
│                   │   ├── BorrowerTest.java
│                   │   └── BorrowingSlipTest.java
│                   └── service/
│                       ├── BookServiceTest.java
│                       ├── BorrowerServiceTest.java
│                       └── BorrowingServiceTest.java
├── lib/
├── .gitignore
├── README.md
└── pom.xml


# Xây dựng ứng dụng quản lý thư viện sử dụng Java Spring Boot với các chức năng cơ bản sau:

Giao diện: Java Spring Boot (hoặc Java Console nếu không có frontend).

Có chức năng quản lý sách:

Thêm, sửa, xóa sách.

Liệt kê thông tin về sách, có thể lọc ra các sách theo thể loại, năm xuất bản, tác giả.

Có chức năng quản lý độc giả:

Thêm, sửa, xóa độc giả.

Có chức năng quản lý phiếu mượn sách:

Thêm, sửa, xóa phiếu mượn.

Có chức năng gán sách cho phiếu mượn, và liên kết độc giả với phiếu mượn.

Dữ liệu được lưu trữ xuống file nhị phân.

Cần tạo các lớp liên quan đến sách, độc giả, và phiếu mượn để đọc, ghi xuống 1 hay nhiều file.

Khi làm việc với dữ liệu trong bộ nhớ, dữ liệu cần được lưu trữ dưới dạng các Collection tùy chọn như ArrayList, LinkedList, Map, ...

Sinh viên có thể thêm các chức năng vào chương trình để ứng dụng phong phú hơn bằng cách thêm các nghiệp vụ cho bài toán (tùy chọn). Ví dụ: thông kê số lượt mượn theo độc giả, tìm sách được mượn nhiều nhất, cảnh báo trễ hạn trả sách,...

link sơ đồ Class Diagram : https://drive.google.com/drive/folders/1ypbB4Mei8aR1sIvo2U265A7XeWCYO035?usp=sharing



















