package com.library.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi tải file: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            System.out.println("Hoàn thành thao tác tải file.");
        }
    }

    public static <T> void saveToFile(List<T> items, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(items);
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu file: " + e.getMessage());
        } finally {
            System.out.println("Hoàn thành thao tác lưu file.");
        }
    }
}