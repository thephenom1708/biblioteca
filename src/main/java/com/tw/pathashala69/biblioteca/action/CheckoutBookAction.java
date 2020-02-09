package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;

public class CheckoutBookAction implements Action {
    private final Books books;

    public CheckoutBookAction(Books books) {
        this.books = books;
    }

    @Override
    public void perform() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        OutputManager.output(outputMessage, System.out);
        InputManager.input(System.in);

        Book bookToCheckout = null;
        try {
            bookToCheckout = books.searchByName("Harry Potter");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

        books.checkout(bookToCheckout);
    }
}
