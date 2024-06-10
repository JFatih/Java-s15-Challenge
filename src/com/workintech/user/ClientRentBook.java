package com.workintech.user;

import com.workintech.books.Books;

import java.time.LocalDate;

public class ClientRentBook {
    private Books rentedBook;
    private LocalDate rentDate;

    public ClientRentBook(Books rentedBook, LocalDate rentDate) {
        this.rentedBook = rentedBook;
        this.rentDate = rentDate;
    }
}
