package com.library.repository;

import com.library.model.Book;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}