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





