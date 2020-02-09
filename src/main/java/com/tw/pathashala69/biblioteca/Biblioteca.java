package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.models.Library;

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
