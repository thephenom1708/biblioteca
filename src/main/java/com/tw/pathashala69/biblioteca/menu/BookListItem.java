package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;
import com.tw.pathashala69.biblioteca.models.Library;

//Job: Represents view all books menu item
public class BookListItem extends BaseMenuItem {
    private final Library library;

    public BookListItem(Library library) {
        super(Message.BOOKS_LIST_OPTION, Symbol.B);
        this.library = library;
    }

    @Override
    public void onSelect() {
        library.books().available().forEach(book -> book.print(System.out));
    }
}
