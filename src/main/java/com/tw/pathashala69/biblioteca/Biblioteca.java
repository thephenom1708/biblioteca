package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.action.BookListViewAction;
import com.tw.pathashala69.biblioteca.action.CheckoutBookAction;
import com.tw.pathashala69.biblioteca.action.QuitAction;
import com.tw.pathashala69.biblioteca.action.ReturnBookAction;
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
        BookListItem bookListItem = new BookListItem(new BookListViewAction(library.books()));
        CheckoutBookItem checkoutBookItem = new CheckoutBookItem(new CheckoutBookAction(library));
        ReturnBookItem returnBookItem = new ReturnBookItem(new ReturnBookAction(library));
        QuitItem quitItem = new QuitItem(new QuitAction());
        return new MainMenu(List.of(bookListItem, checkoutBookItem, returnBookItem, quitItem));
    }
}
