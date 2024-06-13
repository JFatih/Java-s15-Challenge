package com.workintech.librarian;

import com.workintech.books.Books;

import java.util.List;

public interface BookManagement {
    void addBook(Books newBooks);
    void findBook(Long id);
    void findBook(String data);
    void findCategoryBooks(String category);
    void updateBook(Books book);
    void removeBooks(String[] removeBookIds);
}
