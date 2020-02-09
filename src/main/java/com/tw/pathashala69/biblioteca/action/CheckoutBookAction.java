package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Books;
import com.tw.pathashala69.biblioteca.models.Library;

public class CheckoutBookAction implements Action {
    private final Library library;

    public CheckoutBookAction(Library library) {
        this.library = library;
    }

    @Override
    public void perform() {
        String outputMessage = Message.ENTER_INPUT_MESSAGE + " " + "Book Name to checkout: ";
        OutputManager.output(outputMessage, System.out);
        String bookName = InputManager.input(System.in);

        Book bookToCheckout;
        try {
            bookToCheckout = library.books().searchByName(bookName);
            library.checkout(bookToCheckout);
        } catch (BookNotFoundException | BookNotAvailable e) {
            checkoutUnsuccessful();
            return;
        }

        OutputManager.output(Message.CHECKOUT_BOOK_SUCCESS_MESSAGE, System.out);
    }

    private void checkoutUnsuccessful() {
        OutputManager.output(Message.CHECKOUT_BOOK_UNSUCCESSFUL_MESSAGE, System.out);
    }
}
