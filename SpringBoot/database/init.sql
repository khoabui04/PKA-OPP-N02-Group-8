-- Hệ thống quản lý thư viện - Đại học Phenikaa
-- File khởi tạo database và dữ liệu mẫu

-- Tạo database
CREATE DATABASE IF NOT EXISTS library_management 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE library_management;

-- Bảng thủ thư
CREATE TABLE librarians (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'LIBRARIAN', 'ASSISTANT') DEFAULT 'LIBRARIAN',
    status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng độc giả
CREATE TABLE readers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    student_id VARCHAR(20) UNIQUE,
    date_of_birth DATE,
    reader_type ENUM('STUDENT', 'FACULTY', 'STAFF', 'EXTERNAL') DEFAULT 'STUDENT',
    status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE',
    expiry_date DATE NOT NULL,
    max_borrow_books INT DEFAULT 5,
    current_borrowed_books INT DEFAULT 0,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng sách
CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    category VARCHAR(100) NOT NULL,
    publisher VARCHAR(100),
    publication_year INT,
    quantity INT NOT NULL DEFAULT 1,
    available_quantity INT NOT NULL DEFAULT 1,
    description TEXT,
    shelf_location VARCHAR(50),
    status ENUM('AVAILABLE', 'BORROWED', 'MAINTENANCE', 'LOST') DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng mượn/trả sách
CREATE TABLE borrowings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    reader_id BIGINT NOT NULL,
    librarian_id BIGINT,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status ENUM('BORROWED', 'RETURNED', 'OVERDUE', 'LOST', 'DAMAGED') DEFAULT 'BORROWED',
    renewal_count INT DEFAULT 0,
    max_renewals INT DEFAULT 2,
    fine_amount DECIMAL(10,2) DEFAULT 0.00,
    book_condition_borrowed VARCHAR(50),
    book_condition_returned VARCHAR(50),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (reader_id) REFERENCES readers(id) ON DELETE CASCADE,
    FOREIGN KEY (librarian_id) REFERENCES librarians(id) ON DELETE SET NULL
);

-- Bảng thông báo
CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type ENUM('SUCCESS', 'WARNING', 'ERROR', 'INFO', 'REMINDER') DEFAULT 'INFO',
    priority ENUM('LOW', 'NORMAL', 'HIGH', 'URGENT') DEFAULT 'NORMAL',
    target_user_id BIGINT,
    target_user_type ENUM('READER', 'LIBRARIAN', 'ALL') DEFAULT 'ALL',
    related_entity_id BIGINT,
    related_entity_type VARCHAR(50),
    is_read BOOLEAN DEFAULT FALSE,
    read_at TIMESTAMP NULL,
    created_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES librarians(id) ON DELETE SET NULL
);

-- Thêm dữ liệu mẫu
INSERT INTO librarians (name, email, phone, username, password, role, status) VALUES
('Nguyễn Văn Thủ thư', 'thuthu@phenikaa.edu.vn', '0123456789', 'admin', '$2a$10$rDmFN6ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8ZqJ8', 'ADMIN', 'ACTIVE');

INSERT INTO readers (name, email, phone, address, student_id, date_of_birth, reader_type, status, expiry_date, max_borrow_books) VALUES
('Nguyễn Văn A', 'nguyenvana@student.phenikaa.edu.vn', '0123456789', 'Hà Nội', 'SV001', '2000-01-01', 'STUDENT', 'ACTIVE', '2025-12-31', 5),
('Trần Thị B', 'tranthib@student.phenikaa.edu.vn', '0987654321', 'Hà Nội', 'SV002', '2001-02-02', 'STUDENT', 'ACTIVE', '2025-12-31', 5);

INSERT INTO books (title, author, isbn, category, publisher, publication_year, quantity, available_quantity, description, shelf_location) VALUES
('Lập trình Java cơ bản', 'Nguyễn Văn Tác giả', '978-0-123456-47-2', 'Công nghệ', 'NXB Giáo dục', 2023, 10, 10, 'Sách học Java cơ bản cho sinh viên', 'A1-B1-C1'),
('Spring Framework trong thực tế', 'Trần Thị Tác giả', '978-0-123456-48-9', 'Công nghệ', 'NXB Khoa học', 2023, 8, 8, 'Hướng dẫn sử dụng Spring Framework', 'A1-B1-C2'),
('Thiết kế cơ sở dữ liệu', 'Lê Văn Tác giả', '978-0-123456-49-6', 'Công nghệ', 'NXB Thông tin', 2022, 12, 12, 'Nguyên lý thiết kế CSDL', 'A1-B2-C1');

SELECT 'Database library_management đã được tạo thành công!' as message; 