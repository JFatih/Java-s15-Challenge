package com.workintech.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Client extends UserData{

    private Set<ClientRentBook> rentBooks;

    public Client(String name, String surname, String email, String password) {
        super(name, surname, email, password);
        setStatus("Client");
        rentBooks = new HashSet<>();
    }

    public Set<ClientRentBook> getRentBooks() {
        return rentBooks;
    }

    public void setRentBooks(ClientRentBook... rentBook) {
        rentBooks.addAll(Arrays.asList(rentBook));
    }

    public void setRentBooks(Set<ClientRentBook> clientRentBooks) {
        rentBooks.addAll(clientRentBooks);
    }
}
