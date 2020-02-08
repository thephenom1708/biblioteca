package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Book;
import com.tw.pathashala69.biblioteca.action.BookListView;

import java.util.List;

//Job: Represents view all books menu item
public class BookListItem implements MenuItem {
    private final BookListView bookListView;

    public BookListItem(BookListView view) {
        this.bookListView = view;
    }

    @Override
    public String present() {
        return "View all Books";
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
