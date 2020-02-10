package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.menu.*;
import com.tw.pathashala69.biblioteca.models.Library;

import java.util.List;

//Job: Represents Menu for Biblioteca
public class Biblioteca {
    Library library;

    public Biblioteca(Library library) {
        this.library = library;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    public MainMenu mainMenu() {
        BookListItem bookListItem = new BookListItem(library);
        CheckoutBookItem checkoutBookItem = new CheckoutBookItem(library);
        ReturnBookItem returnBookItem = new ReturnBookItem(library);
        QuitItem quitItem = new QuitItem(library);
        return new MainMenu(List.of(bookListItem, checkoutBookItem, returnBookItem, quitItem));
    }
}
