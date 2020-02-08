package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.BookListViewAction;

//Job: Represents view all books menu item
public class BookListItem extends BaseMenuItem {
    public static final String NAME = "List of books";
    public static final String SYMBOL = "B";

    public BookListItem(BookListViewAction bookListViewAction) {
        super(NAME, SYMBOL, bookListViewAction);
    }
}
