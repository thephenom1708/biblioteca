package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.action.BookListView;

//Job: Represents view all books menu item
public class BookListItem implements MenuItem {
    private final BookListView bookListView;

    public BookListItem(BookListView view) {
        this.bookListView = view;
    }

    @Override
    public String present() {
        return "List of books";
    }

    @Override
    public String symbol() {
        return "B";
    }

    @Override
    public void onSelect() {
       bookListView.perform();
    }
}
