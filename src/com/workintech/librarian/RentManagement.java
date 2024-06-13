package com.workintech.librarian;

import com.workintech.books.Books;

public interface RentManagement {
    void updateBookStatus(Books updateBook);
    void rentToBook();
    void returnRentedBooksId(String[] ids);
    void getRentedBooksData();
}
