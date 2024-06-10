package com.workintech.books.category;

import com.workintech.books.BookStatus;
import com.workintech.books.Books;

import java.time.LocalDate;

public class FictionBook extends Books {
    private final String category;

    public FictionBook(String name, String author, int price, String edition, LocalDate dateOfPurchase) {
        super(name, author, price, edition, dateOfPurchase);
        this.category = "Fiction Books";
    }

    @Override
    public String toString() {
        return super.toString() + ", category='" + category + '\'' + '}';
    }
}
