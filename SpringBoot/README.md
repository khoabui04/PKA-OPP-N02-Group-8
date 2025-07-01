# HỆ THỐNG QUẢN LÝ THƯ VIỆN - ĐẠI HỌC PHENIKAA

## 📚 Giới thiệu dự án

Hệ thống Quản lý Thư viện là ứng dụng web sử dụng Java Spring Boot, giúp quản lý sách, độc giả, giao dịch mượn/trả, báo cáo thống kê và các chức năng liên quan cho thư viện đại học.

---

## ✨ Tính năng chính

- **Quản lý sách:** Thêm, sửa, xóa, tìm kiếm, phân loại, theo dõi lịch sử mượn.
- **Quản lý độc giả:** Đăng ký, cập nhật, phân loại, quản lý trạng thái thẻ, theo dõi lịch sử mượn.
- **Quản lý giao dịch mượn/trả:** Tạo giao dịch, trả/gia hạn sách, theo dõi quá hạn, tính phạt.
- **Báo cáo & thống kê:** Dashboard trực quan, báo cáo chi tiết, phân tích dữ liệu.
- **Hệ thống thông báo:** Nhắc nhở quá hạn, thông báo hệ thống, quản lý trạng thái đọc.
- **Cài đặt hệ thống:** Quy định mượn, bảo mật, sao lưu/khôi phục dữ liệu.

---

## 🛠️ Công nghệ sử dụng

- **Backend:** Java 17, Spring Boot 3.2.1, Spring Data JPA, Spring Security, MySQL 8.0, Maven
- **Frontend:** Thymeleaf, Bootstrap 5, Bootstrap Icons, Chart.js, JavaScript
- **Khác:** Lombok, Hibernate, Spring Boot DevTools

---

## 📁 Cấu trúc dự án

```
src/
├── main/
│   ├── java/com/phenikaa/library/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── LibraryManagementApplication.java
│   └── resources/
│       ├── templates/
│       ├── static/
│       └── application.properties
└── test/
```

---

## 🚀 Hướng dẫn cài đặt & sử dụng

### 1. Clone repository
```bash
git clone https://github.com/your-org/your-repo.git
cd your-repo
```

### 2. Cấu hình cơ sở dữ liệu
Tạo database MySQL:
```sql
CREATE DATABASE library_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Chỉnh sửa `src/main/resources/application.properties` với thông tin kết nối MySQL của bạn.

### 3. Build & chạy ứng dụng
```bash
mvn clean install
mvn spring-boot:run
```
Hoặc chạy bằng IDE (IntelliJ/Eclipse) với class `LibraryManagementApplication`.

### 4. Truy cập ứng dụng
Mở trình duyệt: [http://localhost:8080](http://localhost:8080)

---

## 🧑‍💻 Hướng dẫn sử dụng

- **Đăng nhập:**  
  - Username: `admin`  
  - Password: `admin123`

- **Quản lý sách:** Thêm, sửa, xóa, tìm kiếm, xem chi tiết sách.
- **Quản lý độc giả:** Đăng ký, cập nhật, quản lý thẻ, xem lịch sử mượn.
- **Giao dịch mượn/trả:** Tạo giao dịch, trả/gia hạn sách, theo dõi quá hạn.
- **Báo cáo:** Xem dashboard, xuất báo cáo, phân tích dữ liệu.
- **Thông báo:** Xem các thông báo hệ thống, quá hạn, bảo trì.

---

## 🔒 Bảo mật

- Đăng nhập bằng Spring Security, phân quyền theo vai trò.
- Bảo vệ dữ liệu đầu vào, chống SQL Injection, XSS.
- Mã hóa mật khẩu, quản lý session.

---

## 🧪 Kiểm thử

- **Unit Test:**  
  ```bash
  mvn test
  ```
- **Integration Test:**  
  ```bash
  mvn verify
  ```
- **Kiểm tra coverage:**  
  ```bash
  mvn jacoco:report
  ```

---

## 📊 API REST

- `GET /api/books` - Danh sách sách
- `GET /api/books/{id}` - Thông tin sách
- `POST /api/books` - Thêm sách
- `PUT /api/books/{id}` - Sửa sách
- `DELETE /api/books/{id}` - Xóa sách

- `GET /api/readers` - Danh sách độc giả
- `GET /api/readers/{id}` - Thông tin độc giả
- `POST /api/readers` - Thêm độc giả
- `PUT /api/readers/{id}` - Sửa độc giả
- `DELETE /api/readers/{id}` - Xóa độc giả

- `GET /api/borrowings` - Danh sách giao dịch
- `GET /api/borrowings/{id}` - Thông tin giao dịch
- `POST /api/borrowings` - Tạo giao dịch
- `PUT /api/borrowings/{id}/return` - Trả sách
- `PUT /api/borrowings/{id}/renew` - Gia hạn

---

## 🌐 Triển khai (Deployment)

- **Docker:**
  ```bash
  docker build -t library-management .
  docker run -p 8080:8080 library-management
  ```
- **Production:**  
  Build JAR: `mvn clean package`  
  Chạy: `java -jar target/library-management-1.0.0.jar`

---

## 📄 Tài liệu & Báo cáo

- **Sơ đồ UML, Use Case, Class Diagram:** Xem thư mục `docs/` hoặc báo cáo kèm theo.
- **Báo cáo chi tiết:** Theo mẫu Đại học Phenikaa, gồm:  
  - Đường dẫn Github  
  - Video demo Youtube  
  - Link ứng dụng public (nếu có)  
  - Bìa, phân chia công việc, lịch sử commit

---

## 📎 Đường dẫn quan trọng

- [Kho mã nguồn Github](https://github.com/your-org/your-repo)
- [Video demo Youtube](https://youtube.com/your-demo-link)
- [Link ứng dụng chạy thử (nếu có)](https://your-app-link)

---

## 👥 Tác giả & Liên hệ

- **Nhóm phát triển** - Đại học Phenikaa
- **Email:** support@phenikaa.edu.vn
- **Website:** https://phenikaa.edu.vn

---

## 🤝 Đóng góp

1. Fork dự án
2. Tạo branch mới
3. Commit & push thay đổi
4. Tạo Pull Request

---

## 📝 License

Dự án phục vụ mục đích học tập tại Đại học Phenikaa.

---

**Phiên bản:** 1.0.0  
**Cập nhật lần cuối:** Tháng 1, 2024

