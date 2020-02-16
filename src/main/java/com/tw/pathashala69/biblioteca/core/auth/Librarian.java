package com.tw.pathashala69.biblioteca.core.auth;

public class Librarian extends User {
    public Librarian(String libraryNumber, String password, UserPrivilege userPrivilege) {
        super(libraryNumber, password, UserPrivilege.LIBRARIAN);
    }
}
