package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.core.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.core.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Library;

//Job: Represents checkout book item
public class CheckoutBookItem extends BaseMenuItem {
    private final Biblioteca biblioteca;

    public CheckoutBookItem(Biblioteca biblioteca) {
        super(Message.CHECKOUT_BOOK_OPTION, Symbol.CB);
        this.biblioteca = biblioteca;
    }

    @Override
    public void onSelect() {
        String bookName = biblioteca.checkoutBookInput();
        Library library = biblioteca.library();

        Book bookToCheckout;
        try {
            bookToCheckout = library.books().searchByName(bookName);
            library.checkout(bookToCheckout);
        } catch (BookNotFoundException | BookNotAvailable e) {
            biblioteca.onCheckoutBookUnsuccessful();
            return;
        }
        biblioteca.onCheckoutBookSuccess();
    }
}
