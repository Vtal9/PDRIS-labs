package edu.phystech.lab2Spring;

public class User {
    final private String name;
    final private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
