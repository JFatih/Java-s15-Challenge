package com.workintech.librarian;

public interface CartManagement {
    void addToCart(Long[] addToCartIdArray);
    void addToCartAdmin(Long[] addToCartIdArray);
    void removeToCart(Long[] removeBooksIds);
}
