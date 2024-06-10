package com.workintech.user;

import java.util.Set;

public class Client extends UserData{

    private Set<ClientRentBook> rentBooks;

    public Client(String name, String surname, String email, String password) {
        super(name, surname, email, password);
        setStatus("Client");
    }

    public Set<ClientRentBook> getRentBooks() {
        return rentBooks;
    }

    public void setRentBooks(Set<ClientRentBook> rentBooks) {
        this.rentBooks = rentBooks;
    }
}
