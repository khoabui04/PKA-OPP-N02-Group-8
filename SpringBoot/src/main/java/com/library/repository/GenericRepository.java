package com.library.repository;

import com.library.util.FileHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenericRepository<T extends Serializable> {
    private final String filePath;
    private List<T> items;

    public GenericRepository(String filePath) {
        this.filePath = filePath;
        this.items = FileHandler.loadFromFile(filePath);
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
    }

    public void create(T item) {
        try {
            items.add(item);
            FileHandler.saveToFile(items, filePath);
        } catch (Exception e) {
            System.err.println("Lỗi khi tạo: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác tạo.");
        }
    }

    public List<T> readAll() {
        try {
            return new ArrayList<>(items);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc danh sách: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            System.out.println("Hoàn thành thao tác đọc danh sách.");
        }
    }

    public T read(Predicate<T> filter) {
        try {
            return items.stream().filter(filter).findFirst().orElse(null);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc: " + e.getMessage());
            return null;
        } finally {
            System.out.println("Hoàn thành thao tác đọc.");
        }
    }

    public void update(T updatedItem, Predicate<T> filter) {
        try {
            for (int i = 0; i < items.size(); i++) {
                if (filter.test(items.get(i))) {
                    items.set(i, updatedItem);
                    FileHandler.saveToFile(items, filePath);
                    return;
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác cập nhật.");
        }
    }

    public void delete(Predicate<T> filter) {
        try {
            items.removeIf(filter);
            FileHandler.saveToFile(items, filePath);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác xóa.");
        }
    }
}