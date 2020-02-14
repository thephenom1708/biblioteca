package com.tw.pathashala69.biblioteca.core.auth;

public class Session {
    private User user;

    public User user() {
        return user;
    }

    public void start(User user) {
        this.user = user;
    }

    public void destroy() {
        this.user = null;
    }
}
