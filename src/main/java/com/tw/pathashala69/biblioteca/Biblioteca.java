package com.tw.pathashala69.biblioteca;

//Job: Represents Library
public class Biblioteca {
    Library library;

    public Biblioteca(Library library) {
        this.library = library;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }
}
