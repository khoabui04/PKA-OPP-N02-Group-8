package library;

import java.io.*;
import java.util.*;

public class CrudManager<T extends Serializable> {
    private final List<T> list = new ArrayList<>();
    private final String fileName;

    public CrudManager(String fileName) {
        this.fileName = fileName;
        load();
    }

    public void add(T obj) {
        list.add(obj);
    }

    public List<T> getAll() {
        return list;
    }

    public void remove(int index) {
        if (index >= 0 && index < list.size()) list.remove(index);
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void load() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<T> loaded = (List<T>) ois.readObject();
            list.clear();
            list.addAll(loaded);
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
