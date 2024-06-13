package com.workintech.librarian;

import com.workintech.books.Books;


public interface BookManagement {
    void addBook(Books newBooks);
    void findBook(Object data);
    void findCategoryBooks(String category);
    void updateBook(Books book);
    void removeBooks(String[] removeBookIds);
}
