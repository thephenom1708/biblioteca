package com.tw.pathashala69.biblioteca.core.auth;

public class Session {
    private User user;

    public Session(User user) {
        this.user = user;
    }

    public User user() {
        return user;
    }

    public void destroy() {
        this.user = null;
    }
}
