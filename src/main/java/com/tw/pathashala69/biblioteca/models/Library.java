package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.IllegalBookException;

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

    public void returnBook(Book book) throws IllegalBookException {
        books.returnBook(book);
    }
}
