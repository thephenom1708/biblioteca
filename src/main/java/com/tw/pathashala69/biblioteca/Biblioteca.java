package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.menu.*;
import com.tw.pathashala69.biblioteca.models.Library;

import java.io.PrintStream;
import java.util.List;

//Job: Represents Menu for Biblioteca
public class Biblioteca {
    private final Library library;
    private final PrintStream stream;

    public Biblioteca(Library library, PrintStream stream) {
        this.library = library;
        this.stream = stream;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    public MainMenu mainMenu() {
        BookListItem bookListItem = new BookListItem(this);
        CheckoutBookItem checkoutBookItem = new CheckoutBookItem(library);
        ReturnBookItem returnBookItem = new ReturnBookItem(library);
        QuitItem quitItem = new QuitItem(library);
        return new MainMenu(List.of(bookListItem, checkoutBookItem, returnBookItem, quitItem));
    }

    public void printAvailableBooks() {
        library.books().available().forEach(book -> book.print(stream));
    }
}
