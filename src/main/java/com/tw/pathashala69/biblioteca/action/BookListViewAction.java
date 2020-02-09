package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.models.Books;

import java.util.List;

// Job: View details of all books
public class BookListViewAction implements Action {
    Books books;

    public BookListViewAction(Books books) {
        this.books = books;
    }

    @Override
    public void perform() {
        books.forEach(book -> book.print(System.out));
    }
}
