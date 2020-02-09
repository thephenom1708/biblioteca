package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;

//Job: Represents Library
public class Library {
    private final Books books;

    public Library(Books books) {
        this.books = books;
    }

    public Books books() {
        return books;
    }

    public void checkout(Book book) throws BookNotAvailable {
        books.checkout(book);
    }

    public void returnBook(Book book) {
        books.returnBook(book);
    }
}
