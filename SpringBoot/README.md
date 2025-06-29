# Hệ thống Quản lý Thư viện - Đại học Phenikaa

## 📚 Mô tả dự án

Hệ thống Quản lý Thư viện là một ứng dụng web được phát triển bằng Java Spring Boot, cung cấp các chức năng quản lý toàn diện cho thư viện đại học. Hệ thống hỗ trợ quản lý sách, độc giả, giao dịch mượn trả, và các báo cáo thống kê.

## ✨ Tính năng chính

### 📖 Quản lý sách
- Thêm, sửa, xóa thông tin sách
- Tìm kiếm sách theo nhiều tiêu chí (tên, tác giả, ISBN, thể loại)
- Quản lý số lượng sách và trạng thái có sẵn
- Phân loại sách theo thể loại và nhà xuất bản
- Theo dõi lịch sử mượn sách

### 👥 Quản lý độc giả
- Đăng ký và quản lý thông tin độc giả
- Phân loại độc giả (Sinh viên, Giảng viên, Nhân viên, Bên ngoài)
- Quản lý trạng thái thẻ thư viện
- Theo dõi số sách đang mượn và lịch sử mượn

### 🔄 Quản lý giao dịch mượn sách
- Tạo giao dịch mượn sách mới
- Xử lý trả sách và gia hạn
- Theo dõi sách quá hạn và tính phạt
- Quản lý điều kiện sách khi mượn/trả

### 📊 Báo cáo và thống kê
- Dashboard tổng quan với biểu đồ trực quan
- Báo cáo thống kê chi tiết
- Theo dõi sách được mượn nhiều nhất
- Báo cáo độc giả và giao dịch

### 🔔 Hệ thống thông báo
- Thông báo sách quá hạn
- Thông báo sắp đến hạn
- Thông báo hệ thống và bảo trì
- Quản lý trạng thái đọc thông báo

### ⚙️ Cài đặt hệ thống
- Cấu hình quy định mượn sách
- Quản lý thông báo
- Cài đặt bảo mật
- Sao lưu và khôi phục dữ liệu

## 🛠️ Công nghệ sử dụng

### Backend
- **Java 17** - Ngôn ngữ lập trình chính
- **Spring Boot 3.2.1** - Framework web
- **Spring Data JPA** - ORM và quản lý cơ sở dữ liệu
- **Spring Security** - Bảo mật ứng dụng
- **MySQL 8.0** - Hệ quản trị cơ sở dữ liệu
- **Maven** - Quản lý dependencies

### Frontend
- **Thymeleaf** - Template engine
- **Bootstrap 5** - Framework CSS
- **Bootstrap Icons** - Icon library
- **Chart.js** - Thư viện biểu đồ
- **JavaScript** - Tương tác client-side

### Development Tools
- **Lombok** - Giảm boilerplate code
- **Spring Boot DevTools** - Hot reload
- **Hibernate** - JPA implementation

## 📋 Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- MySQL 8.0 hoặc cao hơn
- Maven 3.6+
- ít nhất 2GB RAM
- 1GB dung lượng ổ cứng trống

## 🚀 Hướng dẫn cài đặt

### 1. Clone repository
```bash
git clone https://github.com/your-username/library-management.git
cd library-management
```

### 2. Cấu hình cơ sở dữ liệu
Tạo database MySQL:
```sql
CREATE DATABASE library_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Cấu hình ứng dụng
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_management?useSSL=true&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build và chạy ứng dụng
```bash
# Build project
mvn clean compile

# Chạy ứng dụng
mvn spring-boot:run
```

Hoặc sử dụng IDE:
- Import project vào IntelliJ IDEA hoặc Eclipse
- Chạy class `LibraryManagementApplication`

### 5. Truy cập ứng dụng
Mở trình duyệt và truy cập: `http://localhost:8080`

## 📁 Cấu trúc dự án

```
src/
├── main/
│   ├── java/com/phenikaa/library/
│   │   ├── controller/          # Controllers
│   │   ├── model/              # Entity models
│   │   ├── repository/         # Data access layer
│   │   ├── service/            # Business logic
│   │   └── LibraryManagementApplication.java
│   └── resources/
│       ├── templates/          # Thymeleaf templates
│       ├── static/             # Static resources
│       └── application.properties
└── test/                       # Unit tests
```

## 🔧 Cấu hình môi trường

### Development
```properties
spring.profiles.active=dev
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.com.phenikaa.library=DEBUG
```

### Production
```properties
spring.profiles.active=prod
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.com.phenikaa.library=WARN
```

## 📖 Hướng dẫn sử dụng

### Đăng nhập hệ thống
- Username: `admin`
- Password: `admin123`

### Quản lý sách
1. Vào menu "Quản lý sách"
2. Thêm sách mới hoặc chỉnh sửa sách hiện có
3. Sử dụng tính năng tìm kiếm để tìm sách
4. Xem chi tiết sách và lịch sử mượn

### Quản lý độc giả
1. Vào menu "Quản lý độc giả"
2. Đăng ký độc giả mới
3. Cập nhật thông tin độc giả
4. Quản lý trạng thái thẻ thư viện

### Giao dịch mượn sách
1. Vào menu "Giao dịch mượn sách"
2. Tạo giao dịch mượn mới
3. Xử lý trả sách
4. Gia hạn sách khi cần thiết

### Báo cáo và thống kê
1. Vào menu "Báo cáo"
2. Xem các báo cáo tổng quan
3. Xuất báo cáo chi tiết
4. Phân tích dữ liệu

## 🔒 Bảo mật

### Authentication
- Spring Security với form-based login
- Session management
- Password encryption

### Authorization
- Role-based access control
- URL-based security
- Method-level security

### Data Protection
- Input validation
- SQL injection prevention
- XSS protection

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

### Test Coverage
```bash
mvn jacoco:report
```

## 📊 API Documentation

### REST API Endpoints

#### Books
- `GET /api/books` - Lấy danh sách sách
- `GET /api/books/{id}` - Lấy thông tin sách
- `POST /api/books` - Thêm sách mới
- `PUT /api/books/{id}` - Cập nhật sách
- `DELETE /api/books/{id}` - Xóa sách

#### Readers
- `GET /api/readers` - Lấy danh sách độc giả
- `GET /api/readers/{id}` - Lấy thông tin độc giả
- `POST /api/readers` - Thêm độc giả mới
- `PUT /api/readers/{id}` - Cập nhật độc giả
- `DELETE /api/readers/{id}` - Xóa độc giả

#### Borrowings
- `GET /api/borrowings` - Lấy danh sách giao dịch
- `GET /api/borrowings/{id}` - Lấy thông tin giao dịch
- `POST /api/borrowings` - Tạo giao dịch mới
- `PUT /api/borrowings/{id}/return` - Trả sách
- `PUT /api/borrowings/{id}/renew` - Gia hạn sách

## 🚀 Deployment

### Docker
```bash
# Build Docker image
docker build -t library-management .

# Run container
docker run -p 8080:8080 library-management
```

### Production Deployment
1. Build JAR file: `mvn clean package`
2. Upload JAR file lên server
3. Chạy: `java -jar library-management-1.0.0.jar`

## 🤝 Đóng góp

1. Fork dự án
2. Tạo feature branch: `git checkout -b feature/AmazingFeature`
3. Commit changes: `git commit -m 'Add some AmazingFeature'`
4. Push to branch: `git push origin feature/AmazingFeature`
5. Tạo Pull Request

## 📝 License

Dự án này được phát triển cho mục đích học tập tại Đại học Phenikaa.

## 👥 Tác giả

- **Nhóm phát triển** - Đại học Phenikaa
- **Email**: support@phenikaa.edu.vn
- **Website**: https://phenikaa.edu.vn

## 🙏 Lời cảm ơn

- Spring Boot team
- Bootstrap team
- Cộng đồng open source
- Giảng viên hướng dẫn

## 📞 Hỗ trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra documentation
2. Tìm kiếm trong issues
3. Tạo issue mới
4. Liên hệ: support@phenikaa.edu.vn

---

**Phiên bản**: 1.0.0  
**Cập nhật lần cuối**: Tháng 1, 2024

## Đường dẫn quan trọng

- [Kho mã nguồn Github](https://github.com/your-org/your-repo)
- [Video demo Youtube](https://youtube.com/your-demo-link)
- [Link ứng dụng chạy thử (nếu có)](https://your-app-link)