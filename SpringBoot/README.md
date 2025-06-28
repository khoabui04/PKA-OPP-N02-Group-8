# PKA-OPP-N02-Group-8 - Quản lý thư viện

Tổng quan dự án
Đây là dự án nhóm xây dựng Hệ thống Quản lý Thư viện Sách, sử dụng Java Spring Boot. Ứng dụng quản lý sách, người mượn và bản ghi mượn sách, với các chức năng như thêm, sửa, xóa, liệt kê và giao diện người dùng.
Thành viên nhóm

Sinh viên A: Thực hiện chức năng liên quan đến Sách và giao diện.
Sinh viên B: Thực hiện chức năng liên quan đến Người mượn và kiểm thử.
Sinh viên C: Thực hiện chức năng liên quan đến Bản ghi mượn và tài liệu.

Mục tiêu dự án

Xây dựng ứng dụng Spring Boot theo mô hình MVC.
Quản lý ít nhất ba đối tượng: Sách, Người mượn, Bản ghi mượn.
Hỗ trợ thao tác CRUD cho tất cả đối tượng.
Lưu trữ dữ liệu vào file nhị phân bằng serialization.
Cung cấp giao diện người dùng cho các chức năng chính.
Tài liệu hóa dự án bằng sơ đồ UML và README chi tiết.

Cấu trúc thư mục
/LibraryManagementSystem
├── /src
│   ├── /main
│   │   ├── /java/com/library
│   │   │   ├── /controller
│   │   │   ├── /model
│   │   │   ├── /service
│   │   │   ├── /repository
│   │   │   ├── /util
│   │   ├── /resources
│   ├── /test
├── /docs
├── README.md
├── pom.xml

Các đối tượng và lớp

Sách (Book): Đại diện cho sách với các thuộc tính bookId, title, author, isAvailable.
Người mượn (Borrower): Đại diện cho người dùng với các thuộc tính borrowerId, name, email.
Bản ghi mượn (BorrowingRecord): Theo dõi giao dịch mượn với các thuộc tính recordId, bookId, borrowerId, borrowDate, dueDate.

Chức năng chính

Liệt kê sách đã mượn: Hiển thị danh sách sách được mượn bởi một người mượn.
Kiểm tra sách sắp đến hạn: Liệt kê các sách có ngày đến hạn trong 3 ngày.
Gán sách cho người mượn: Cập nhật trạng thái sách và tạo bản ghi mượn.

Thao tác CRUD

Đã triển khai CRUD cho Sách, Người mượn và Bản ghi mượn.
Sử dụng GenericRepository để tái sử dụng mã CRUD.
Dữ liệu được lưu vào file nhị phân bằng serialization.

Kiểm thử

Các bài kiểm thử: Nằm trong /src/test/java/com/library.
Phạm vi kiểm thử:
BookServiceTest: Kiểm tra CRUD cho Sách.
BorrowerServiceTest: Kiểm tra CRUD cho Người mượn.
BorrowingRecordServiceTest: Kiểm tra CRUD và chức năng chính (sách sắp đến hạn).


Phương pháp kiểm thử:
Sử dụng JUnit 5 cho kiểm thử đơn vị.
Kiểm tra các thao tác tạo, đọc, sửa, xóa.
Kiểm tra lưu trữ file nhị phân với dữ liệu giả lập.



Sơ đồ UML

Sơ đồ lớp: /docs/class_diagram.png
Sơ đồ tuần tự: /docs/sequence_diagram.png
Sơ đồ hoạt động: /docs/activity_diagram.png

Hướng dẫn cài đặt

Sao chép kho mã nguồn: git clone [liên kết repo].
Cài đặt phụ thuộc Maven: mvn clean install.
Chạy ứng dụng: mvn spring-boot:run.
Truy cập giao diện tại http://localhost:8080.

Cải tiến trong tương lai

Tích hợp với cơ sở dữ liệu MySQL (ví dụ: Aiven).
Cải thiện giao diện người dùng.
Thêm chức năng tìm kiếm và lọc nâng cao.

Liên kết

Kho mã nguồn: [Liên kết GitHub]
Video demo: [Liên kết YouTube]
Ứng dụng công khai: [Liên kết công khai]

Phân chia công việc

Sinh viên A: Xây dựng giao diện và chức năng listBooksNearDueDate.
Sinh viên B: Viết kiểm thử và chức năng kiểm tra ngày đến hạn.
Sinh viên C: Tài liệu hóa và kiểm thử tổng thể.
