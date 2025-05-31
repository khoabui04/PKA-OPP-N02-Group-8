# PKA-OPP-N02-Group-8 - Quản lý thư viện

#1. Thành viên

Nguyễn Thị Minh Hằng
Bùi Văn Khoa
Đỗ Vân Khánh

#2. Tiêu đề

Quản lý thư viện

#3. Đối tượng quản lý

Book (Sách)
Borrower (Người mượn)
BorrowingSlip (Phiếu mượn)

#4. Mô tả các lớp chính

Book

Mã sách, tên sách, tác giả
Borrower

Mã người mượn, tên, số điện thoại, danh sách sách đang mượn
BorrowingSlip

Mã phiếu, người mượn, sách, ngày mượn, hạn trả

5. Cấu trúc thư mục Project


PKA-OPP-N02-Group-8/├── src/│   └── main/│       └── java/│           └── library/│               ├── Book.java│               ├── BookManager.java│               ├── Borrower.java│               ├── BorrowerManager.java│               ├── BorrowingSlip.java│               ├── BorrowingSlipManager.java│               ├── CrudManager.java│               ├── QuanLi.java│               ├── TestBook.java│               ├── TestBorrower.java│               └── TestBorrowingSlip.java├── README.md└── ...

6. Chức năng chính

Quản lý sách: Thêm, sửa, xóa, liệt kê, tìm kiếm, lọc sách.
Quản lý người mượn: Thêm, sửa, xóa, liệt kê người mượn.
Quản lý phiếu mượn: Thêm, sửa, xóa, liệt kê phiếu mượn, gán sách cho phiếu mượn, liên kết người mượn với phiếu mượn.
Lưu trữ dữ liệu: Dữ liệu được lưu trữ bằng các Collection như ArrayList, lưu file nhị phân.
Chức năng mở rộng: Thống kê, cảnh báo trễ hạn, tìm sách mượn nhiều nhất...

7. Sơ đồ UML

Class Diagram: http://[https://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing
Sequence Diagram: http://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing
Activity Diagram (bổ sung nếu có)

8. Hướng dẫn chạy chương trình

Biên dịch toàn bộ mã nguồn


javac -d out src/main/java/library/*.java
Chạy từng chức năng


java -cp out library.BookManagerjava -cp out library.BorrowerManagerjava -cp out library.BorrowingSlipManagerjava -cp out library.QuanLi

9. CRUD cho 3 đối tượng

Book: CRUD trong BookManager.java
Borrower: CRUD trong BorrowerManager.java
BorrowingSlip: CRUD trong BorrowingSlipManager.java
10. Kiểm thử/Test

Các file kiểm thử

TestBook.java
TestBorrower.java
TestBorrowingSlip.java
Cách chạy kiểm thử


java -cp out library.TestBookjava -cp out library.TestBorrowerjava -cp out library.TestBorrowingSlip

