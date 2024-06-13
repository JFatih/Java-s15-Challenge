package com.workintech.user;

public abstract class UserData {
    private static int idCounter = 10000;
    private final int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String status;



    public UserData(String name, String surname, String email, String password) {
        this.id = idCounter++;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
