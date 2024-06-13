package com.workintech.librarian;

import com.workintech.user.UserData;

import java.util.TreeMap;

public interface LibraryManagement {
    boolean logIn(String email, String Password);
    void logOut();
    boolean loggedStatus();
    void addUser(UserData newUserData);
}

