package com.tw.pathashala69.biblioteca.core.auth;

public class Customer extends User {
    public Customer(String libraryNumber, String password, UserPrivilege userPrivilege) {
        super(libraryNumber, password, UserPrivilege.CUSTOMER);
    }
}
