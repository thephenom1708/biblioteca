package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.core.models.Books;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;

import java.io.PrintStream;

//Job: Represents Menu for Biblioteca
public class Biblioteca implements UserInterface {
    private final Library library;
    private final PrintStream stream;

    public Biblioteca(Library library, PrintStream stream) {
        this.library = library;
        this.stream = stream;
    }

    public Library library() {
        return library;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    @Override
    public String checkoutBookInput() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        OutputManager.output(outputMessage, System.out);
        return InputManager.input(System.in);
    }

    @Override
    public String returnBookInput() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        OutputManager.output(outputMessage, System.out);
        return InputManager.input(System.in);
    }

    @Override
    public void printBooks(Books books) {
        books.forEach(book -> book.print(stream));
    }

    @Override
    public void onCheckoutBookUnsuccessful() {
        OutputManager.output(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }

    @Override
    public void onCheckoutBookSuccess() {
        OutputManager.output(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE, System.out);
    }

    @Override
    public void onReturnBookUnsuccessful() {
        OutputManager.output(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }

    @Override
    public void onReturnBookSuccess() {
        OutputManager.output(Message.RETURN_BOOK_SUCCESS_MESSAGE, System.out);
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
