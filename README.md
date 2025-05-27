# PKA-OPP-N02-Group-8 - Quản lý thư viện

## 1. Thành viên
- Nguyễn Thị Minh Hằng  
- Bùi Văn Khoa  
- Đỗ Vân Khánh

---

## 2. Tiêu đề
**Quản lý thư viện**

---

## 3. Đối tượng quản lý

- **Book (Sách)**
- **Borrower (Người mượn)**
- **BorrowingSlip (Phiếu mượn)**

---

## 4. Mô tả các lớp chính

### class Book
- `String bookID`
- `String title`
- `String author`
- `String publisher`
- `int publishYear`
- `String genre`
- `boolean available`
- `String location`
- `String isbn`
- `int pageCount`
- `String condition`

### class Borrower
- `String borrowerID`
- `String name`
- `String contactInfo`
- `String address`
- `Date dateOfBirth`
- `Date membershipDate`
- `List<Book> currentBorrowings`
- `List<BorrowingSlip> borrowingHistory`
- `double fineAmount`
- `String status`

### class BorrowingSlip
- `String slipID`
- `Book book`
- `Borrower borrower`
- `Date borrowDate`
- `Date dueDate`
- `Date returnDate`
- `double fine`
- `int renewalCount`
- `String status`
- `String staffID`
- `String damageNotes`

---

## 5. Cấu trúc thư mục Project

```
PKA-OPP-N02-Group-8/
├── src/
│   └── main/
│       └── java/
│           └── thuvien/
│               ├── Book.java
│               ├── BookManager.java
│               ├── Borrower.java
│               ├── BorrowerManager.java
│               ├── BorrowingSlip.java
│               ├── BorrowingSlipManager.java
│               ├── QuanLi.java
│               ├── TestBook.java
│               ├── TestBorrower.java
│               └── TestBorrowingSlip.java
├── .vscode/
│   └── settings.json
├── README.md
└── ...
```

---

## 6. Chức năng chính

- **Quản lý sách:** Thêm, sửa, xóa, liệt kê, tìm kiếm, lọc sách.
- **Quản lý người mượn:** Thêm, sửa, xóa, liệt kê người mượn.
- **Quản lý phiếu mượn:** Thêm, sửa, xóa, liệt kê phiếu mượn, gán sách cho phiếu mượn, liên kết người mượn với phiếu mượn.
- **Lưu trữ dữ liệu:** Dữ liệu được lưu trữ bằng các Collection như ArrayList, có thể mở rộng lưu file nhị phân.
- **Chức năng mở rộng:** Thống kê, cảnh báo trễ hạn, tìm sách mượn nhiều nhất...

---

## 7. Sơ đồ Class Diagram

- Class Diagram:[https://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing]
---

## 8. Sơ đồ Behavioural Diagram

- Sequence Diagram:[https://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing](


- Activity Diagram: 


---

## 9. Hướng dẫn chạy chương trình

### 9.1. Biên dịch toàn bộ mã nguồn

```sh
javac -d out src/main/java/thuvien/*.java
```

### 9.2. Chạy từng chức năng

- **Quản lý sách:**  
  `java -cp out thuvien.BookManager`
- **Quản lý người mượn:**  
  `java -cp out thuvien.BorrowerManager`
- **Quản lý phiếu mượn:**  
  `java -cp out thuvien.BorrowingSlipManager`
- **Chạy demo tổng quan:**  
  `java -cp out thuvien.QuanLi`

---

## 10. CRUD cho 3 đối tượng

- **Book:** CRUD trong `BookManager.java`
- **Borrower:** CRUD trong `BorrowerManager.java`
- **BorrowingSlip:** CRUD trong `BorrowingSlipManager.java`

---

## 11. Kiểm thử/Test

### 11.1. Các file kiểm thử

- `TestBook.java`: Kiểm thử tạo, sửa, xóa sách.
- `TestBorrower.java`: Kiểm thử tạo, sửa, xóa người mượn.
- `TestBorrowingSlip.java`: Kiểm thử tạo, sửa, xóa phiếu mượn.

### 11.2. Cách chạy kiểm thử

```sh
java -cp out thuvien.TestBook
java -cp out thuvien.TestBorrower
java -cp out thuvien.TestBorrowingSlip
```

### 11.3. Nội dung kiểm thử

- Tạo mới đối tượng, kiểm tra thông tin.
- Thêm vào danh sách, kiểm tra số lượng.
- Sửa thông tin, kiểm tra cập nhật.
- Xóa đối tượng, kiểm tra danh sách.
- Kiểm tra liên kết giữa các đối tượng (ví dụ: Borrower mượn Book, tạo BorrowingSlip).

---

## 12. Liên hệ

Mọi thắc mắc vui lòng liên hệ nhóm qua email hoặc github.

