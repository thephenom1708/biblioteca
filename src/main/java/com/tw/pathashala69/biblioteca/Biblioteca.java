package com.tw.pathashala69.biblioteca;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.exception.IllegalBookException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.menu.*;
import com.tw.pathashala69.biblioteca.models.Book;
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

    public Library library() {
        return library;
    }

    public static String welcome() {
        return Message.WELCOME_MESSAGE;
    }

    public MainMenu mainMenu() {
        BookListItem bookListItem = new BookListItem(this);
        CheckoutBookItem checkoutBookItem = new CheckoutBookItem(this);
        ReturnBookItem returnBookItem = new ReturnBookItem(this);
        QuitItem quitItem = new QuitItem(this);
        return new MainMenu(List.of(bookListItem, checkoutBookItem, returnBookItem, quitItem));
    }

    public void printAvailableBooks() {
        library.books().available().forEach(book -> book.print(stream));
    }

    public void checkoutBook() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        OutputManager.output(outputMessage, System.out);
        String bookName = InputManager.input(System.in);

        Book bookToCheckout;
        try {
            bookToCheckout = library().books().searchByName(bookName);
            library().checkout(bookToCheckout);
        } catch (BookNotFoundException | BookNotAvailable e) {
            checkoutBookUnsuccessful();
            return;
        }
        checkoutBookSuccessful();
    }

    public void returnBook() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to Return: ";
        OutputManager.output(outputMessage, System.out);
        String bookName = InputManager.input(System.in);

        Book bookToReturn;
        try {
            bookToReturn = library.books().searchByName(bookName);
            library.returnBook(bookToReturn);
        } catch (BookNotFoundException | IllegalBookException e) {
            returnBookUnsuccessful();
            return;
        }
        returnBookSuccessful();
    }

    public void exit() {
        System.exit(0);
    }

    private void checkoutBookSuccessful() {
        OutputManager.output(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE, System.out);
    }

    private void checkoutBookUnsuccessful() {
        OutputManager.output(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }

    private void returnBookSuccessful() {
        OutputManager.output(Message.RETURN_BOOK_SUCCESS_MESSAGE, System.out);
    }

    private void returnBookUnsuccessful() {
        OutputManager.output(Message.RETURN_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }
}
