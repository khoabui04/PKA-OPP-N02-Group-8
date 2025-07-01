# PKA-OPP-N02-Group-8 - Quản lý thư viện

## 1. Thành viên
- Nguyễn Thị Minh Hằng
- Bùi Văn Khoa
- Đỗ Vân Khánh

## 2. Tiêu đề
**Quản lý thư viện**

## 3. Đối tượng quản lý
- **Book (Sách):** Mã sách, tên sách, tác giả
- **Borrower (Người mượn):** Mã người mượn, tên, số điện thoại, danh sách sách đang mượn
- **BorrowingSlip (Phiếu mượn):** Mã phiếu, người mượn, sách, ngày mượn, hạn trả

## 4. Cấu trúc thư mục Project
```
PKA-OPP-N02-Group-8/
├── src/main/java/library/
│   ├── Book.java
│   ├── BookManager.java
│   ├── Borrower.java
│   ├── BorrowerManager.java
│   ├── BorrowingSlip.java
│   ├── BorrowingSlipManager.java
│   ├── CrudManager.java
│   ├── QuanLi.java
│   ├── TestBook.java
│   ├── TestBorrower.java
│   └── TestBorrowingSlip.java
├── README.md
└── ...
```

## 5. Chức năng chính
- Quản lý sách: Thêm, sửa, xóa, liệt kê, tìm kiếm, lọc sách.
- Quản lý người mượn: Thêm, sửa, xóa, liệt kê người mượn.
- Quản lý phiếu mượn: Thêm, sửa, xóa, liệt kê phiếu mượn, gán sách cho phiếu mượn, liên kết người mượn với phiếu mượn.
- Lưu trữ dữ liệu: Dữ liệu được lưu trữ bằng các Collection như ArrayList, lưu file nhị phân.
- Chức năng mở rộng: Thống kê, cảnh báo trễ hạn, tìm sách mượn nhiều nhất.

## 6. Sơ đồ UML
- **Class Diagram:** [Xem tại đây](https://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing)
- **Sequence Diagram:** [Xem tại đây](https://drive.google.com/file/d/1nDPU4V0jP4qDLwa3WDVNuQa_KLGO881w/view?usp=sharing)
- **Activity Diagram:** (Bổ sung nếu có)

## 7. Hướng dẫn chạy chương trình
- Biên dịch toàn bộ mã nguồn:
  ```bash
  javac -d out src/main/java/library/*.java
  ```
- Chạy chương trình quản lý:
  ```bash
  java -cp out library.QuanLi
  ```

## 8. CRUD cho 3 đối tượng
- **Book:** CRUD trong `BookManager.java`
- **Borrower:** CRUD trong `BorrowerManager.java`
- **BorrowingSlip:** CRUD trong `BorrowingSlipManager.java`

## 9. Kiểm thử/Test
- Các file kiểm thử: `TestBook.java`, `TestBorrower.java`, `TestBorrowingSlip.java`
- Chạy kiểm thử:
  ```bash
  java -cp out library.TestBook
  java -cp out library.TestBorrower
  java -cp out library.TestBorrowingSlip
  ```

## 10. Lưu đồ thuật toán cảnh báo sách gần đến hạn trả
```
Bắt đầu
   |
Nhập mã bạn đọc
   |
Lấy danh sách phiếu mượn của bạn đọc
   |
Kiểm tra từng phiếu mượn:
   |-- Nếu ngày trả còn ≤ 3 ngày --> Thêm vào danh sách cảnh báo
   |-- Ngược lại --> Bỏ qua
   |
In danh sách cảnh báo
   |
Kết thúc
```

## 11. Chức năng cảnh báo sách gần đến hạn trả
- Thông báo danh sách sách đã mượn bởi bạn đọc, gần đến ngày phải trả.

## 12. Phân chia công việc
- Nguyễn Thị Minh Hằng: Viết hàm lấy danh sách phiếu mượn
- Bùi Văn Khoa: Viết hàm kiểm tra gần đến hạn trả
- Đỗ Vân Khánh: Viết hàm tổng hợp và in cảnh báo

## 13. Ảnh giao diện chức năng cảnh báo sách gần đến hạn trả
- [Ảnh chạy chương trình, giao diện cảnh báo sách gần đến hạn trả](https://drive.google.com/drive/folders/1br1l1kf0BAGJCVGVEAaElXbE0fobXPOs?usp=sharing)




