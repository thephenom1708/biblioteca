package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import com.tw.pathashala69.biblioteca.io.InputManager;
import com.tw.pathashala69.biblioteca.io.OutputManager;
import com.tw.pathashala69.biblioteca.models.Book;
import com.tw.pathashala69.biblioteca.models.Library;

//Job: Represents checkout book item
public class CheckoutBookItem extends BaseMenuItem {
    private final Biblioteca biblioteca;

    public CheckoutBookItem(Biblioteca biblioteca) {
        super(Message.CHECKOUT_BOOK_OPTION, Symbol.CB);
        this.biblioteca = biblioteca;
    }

    @Override
    public void onSelect() {
        biblioteca.checkoutBook();
    }
}
