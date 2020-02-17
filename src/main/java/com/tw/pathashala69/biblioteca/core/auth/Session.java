package com.tw.pathashala69.biblioteca.core.auth;

public class Session {
    public static final String GUEST_LIBRARY_NUMBER = "000-0000";
    public static final String GUEST_PASSWORD = "pass";
    private User user;

    public Session(User user) {
        this.user = user;
    }

    public User user() {
        return user;
    }

    public void destroy() {
        this.user = new Guest(GUEST_LIBRARY_NUMBER, GUEST_PASSWORD, UserPrivilege.GUEST);
    }
}
