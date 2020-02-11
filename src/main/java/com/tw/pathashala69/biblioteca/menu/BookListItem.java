package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

//Job: Represents view all books menu item
public class BookListItem extends BaseMenuItem {
    private final Biblioteca biblioteca;

    public BookListItem(Biblioteca biblioteca) {
        super(Message.BOOKS_LIST_OPTION, Symbol.B);
        this.biblioteca = biblioteca;
    }

    @Override
    public void onSelect() {
        biblioteca.printAvailableBooks();
    }
}
