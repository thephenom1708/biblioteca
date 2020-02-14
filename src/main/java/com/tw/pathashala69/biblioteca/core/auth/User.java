package com.tw.pathashala69.biblioteca.core.auth;

import java.util.Objects;

public abstract class User {
    private final String libraryNumber;
    private final String password;
    private boolean isLoggedIn;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.isLoggedIn = false;
    }

    public String libraryNumber() {
        return libraryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return libraryNumber.equals(user.libraryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber);
    }

    public boolean authenticate(String libraryNumber, String password) {
        return libraryNumber.equals(this.libraryNumber) && password.equals(this.password);
    }

    public void loggedIn() {
        isLoggedIn = true;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
