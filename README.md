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



 Quản lý Thư viện
Nhóm 08

Thành viên:

Nguyễn Thị Minh Hằng


Bùi Văn Khoa


Đỗ Vân Khánh


Nội dung bài tập:
Mục tiêu:

Xây dựng ứng dụng quản lý thư viện sử dụng Java Spring Boot với các chức năng cơ bản sau:

Yêu cầu:

Giao diện:
Giao diện ứng dụng xây dựng trên nền Java Spring Boot.

Chức năng quản lý sách:

Thêm, sửa, xóa thông tin sách trong thư viện.

Liệt kê danh sách sách, có thể lọc sách theo thể loại, tác giả hoặc năm xuất bản.

Chức năng quản lý độc giả:

Thêm, sửa, xóa thông tin độc giả.

Liệt kê danh sách độc giả, có thể lọc theo nhóm độc giả (sinh viên, giảng viên, khách).

Chức năng quản lý mượn trả:

Quản lý việc mượn sách của độc giả.

Quản lý việc trả sách, cập nhật tình trạng sách.

Chức năng gán sách cho độc giả:

Cho phép gán một hoặc nhiều sách cho một độc giả khi mượn.

Lưu trữ dữ liệu:

Dữ liệu được lưu trữ xuống file nhị phân để đảm bảo tính bền vững.

Cần tạo các lớp liên quan đến Sách, Độc giả, Phiếu mượn để đọc, ghi xuống một hoặc nhiều file.

Xử lý dữ liệu trong bộ nhớ:

Khi làm việc với dữ liệu trong bộ nhớ, dữ liệu cần được lưu trữ dưới dạng các Collection tuỳ chọn như ArrayList, LinkedList, Map, … tùy yêu cầu bài toán.

Mở rộng (tùy chọn):

Sinh viên có thể thêm các chức năng mở rộng như báo cáo thống kê, tìm kiếm nâng cao, nhắc nhở quá hạn,... để làm ứng dụng phong phú hơn.





