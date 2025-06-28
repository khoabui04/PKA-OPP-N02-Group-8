# Quản lý thư viện - Spring Boot

## Mô tả
Ứng dụng quản lý thư viện với các chức năng quản lý Sách, Người mượn, Bản ghi mượn. Lưu trữ dữ liệu bằng file nhị phân, giao diện web với Thymeleaf.

## Chức năng
- CRUD Sách, Người mượn, Bản ghi mượn
- Liệt kê sách đã mượn, sách sắp đến hạn trả
- Giao diện web đơn giản

## Chức năng mở rộng
- Kết nối MySQL Cloud (Aiven)
- Spring Data JPA
- Đăng nhập, phân quyền (Spring Security)
- Tìm kiếm, phân trang
- API REST
- Giao diện Bootstrap

## Cài đặt & chạy
```sh
cd SpringBoot
mvn clean install
mvn spring-boot:run
```
Truy cập [http://localhost:8080](http://localhost:8080)

## Kiểm thử
```sh
mvn test
```

## Hướng dẫn sử dụng
- Xem chi tiết từng phần trong các file:
    - application.properties: cấu hình MySQL
    - BookRepository.java: JPA, tìm kiếm, phân trang
    - SecurityConfig.java: bảo mật
    - BookRestController.java: API REST
    - book_list.html: giao diện, tìm kiếm, phân trang, Bootstrap

## Thành viên nhóm
- Sinh viên A: Sách, giao diện
- Sinh viên B: Người mượn, kiểm thử
- Sinh viên C: Bản ghi mượn, tài liệu

## Demo, báo cáo, UML, phân chia công việc: [đường dẫn Google Drive/Youtube/Github]
