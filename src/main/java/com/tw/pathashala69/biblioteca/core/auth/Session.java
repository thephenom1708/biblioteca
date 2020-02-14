package com.tw.pathashala69.biblioteca.core.auth;

public class Session {
    private User user;

    public void start(User user) {
        this.user = user;
    }

    public User user() {
        return user;
    }
}
