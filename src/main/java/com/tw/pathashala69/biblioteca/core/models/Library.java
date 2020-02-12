package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;

//Job: Represents Library
public class Library {
    private final Borrowables books;

    public Library(Borrowables books) {
        this.books = books;
    }

    public Borrowables books() {
        return books;
    }

    public void checkout(Borrowable borrowable) throws BorrowableNotAvailableException {
        books.checkout(borrowable);
    }

    public void returnBorrowable(Borrowable borrowable) throws IllegalBorrowableException {
        books.returnBorrowable(borrowable);
    }
}
