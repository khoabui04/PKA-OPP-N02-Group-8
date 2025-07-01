package library;

import java.io.*;
import java.util.*;

public class CrudManager<T extends Serializable> {
    private final List<T> list = new ArrayList<>();
    private final String fileName;

    public CrudManager(String fileName) {
        this.fileName = fileName;
        try {
            load();
        } catch (Exception e) {
            System.out.println("Lỗi khi khởi tạo CrudManager: " + e.getMessage());
        }
    }

    public void add(T obj) {
        try {
            if (obj == null) {
                throw new IllegalArgumentException("Không thể thêm đối tượng null.");
            }
            list.add(obj);
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm đối tượng: " + e.getMessage());
        }
    }

    public List<T> getAll() {
        try {
            return new ArrayList<>(list); // Tránh trả về danh sách gốc
        } catch (Exception e) {
            System.out.println("Lỗi khi truy xuất danh sách: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void remove(int index) {
        try {
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException("Chỉ số không hợp lệ: " + index);
            }
            list.remove(index);
        } catch (Exception e) {
            System.out.println("Lỗi khi xoá phần tử: " + e.getMessage());
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định khi lưu: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void load() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                list.clear();
                list.addAll((List<T>) obj);
            } else {
                throw new ClassCastException("Dữ liệu không đúng định dạng danh sách.");
            }
        } catch (IOException e) {
            System.out.println("Lỗi IO khi đọc file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp khi đọc file: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Lỗi ép kiểu khi đọc danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
} 