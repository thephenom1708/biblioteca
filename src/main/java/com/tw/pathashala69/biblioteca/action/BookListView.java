package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.Book;

import java.util.List;

// Job: Action to be performed when booklist menu item is selected
public class BookListView implements Action {
    public List<Book> books;

    public BookListView(List<Book> books) {
        this.books = books;
    }

    @Override
    public void perform() {
        books.forEach(book -> book.print(System.out));
    }
}
