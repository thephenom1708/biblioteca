package com.tw.pathashala69.biblioteca.core.auth;

public class Guest extends User {
    public Guest(String libraryNumber, String password, UserPrivilege privilege) {
        super(libraryNumber, password, UserPrivilege.GUEST);
    }
}
