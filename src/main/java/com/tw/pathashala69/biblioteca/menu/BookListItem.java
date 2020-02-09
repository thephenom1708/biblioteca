package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.BookListViewAction;
import com.tw.pathashala69.biblioteca.constants.Message;
import com.tw.pathashala69.biblioteca.constants.Symbol;

//Job: Represents view all books menu item
public class BookListItem extends BaseMenuItem {
    public BookListItem(BookListViewAction bookListViewAction) {
        super(Message.BOOKS_LIST_OPTION, Symbol.B, bookListViewAction);
    }
}
