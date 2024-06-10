package com.workintech.user;

public class Admin extends UserData{
    private String status;

    public Admin(String name, String surname, String email, String password) {
        super(name, surname, email, password);
        setStatus("Admin");
    }

}
