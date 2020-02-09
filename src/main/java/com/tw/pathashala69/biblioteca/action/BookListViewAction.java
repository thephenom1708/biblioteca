package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.models.Books;

// Job: View details of all books
public class BookListViewAction implements Action {
    private final Books books;

    public BookListViewAction(Books books) {
        this.books = books;
    }

    @Override
    public void perform() {
        books.available().forEach(book -> book.print(System.out));
    }
}
