package com.workintech.books.category;

import com.workintech.books.BookStatus;
import com.workintech.books.Books;

import java.time.LocalDate;

public class EducationalBook extends Books {
    private final String category;

    public EducationalBook(String name, String author, int price, String edition, LocalDate dateOfPurchase) {
        super(name, author, price, edition, dateOfPurchase);
        this.category ="Education Books";
    }

    public int compareTo(EducationalBook other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return super.toString() + ", category='" + category + '\'' + '}';
    }




}
