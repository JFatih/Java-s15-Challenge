package com.workintech.books;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Books {
    private static Long idCounter = 1L;
    private Long id;
    private String name;
    private String author;
    private int price;
    private BookStatus status;
    private String edition;
    private LocalDate dateOfPurchase;

    public Books(String name, String author, int price, String edition, LocalDate dateOfPurchase) {
        this.id = idCounter++;
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", edition='" + edition + '\'' +
                ", dateOfPurchase=" + dateOfPurchase
                ;
    }
}
