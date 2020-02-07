package com.tw.pathashala69.biblioteca;

public class Biblioteca {

    private final String welcomeMessage;

    public Biblioteca() {
        this.welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String welcome() {
        return welcomeMessage;
    }
}
