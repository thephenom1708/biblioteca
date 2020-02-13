package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;

//Job: Represents Library
public class Library {
    private final Borrowables books;
    private final Borrowables movies;

    public Library(Borrowables books, Borrowables movies) {
        this.books = books;
        this.movies = movies;
    }

    public Borrowables books() {
        return books;
    }

    public Borrowables movies() {
        return movies;
    }

    public void checkout(Borrowable borrowable) throws BorrowableNotAvailableException {
        if (books.isCheckedOut(borrowable))
            throw new BorrowableNotAvailableException();
        books.borrowableCheckedOut(borrowable);
    }

    public void returnBorrowable(Borrowable borrowable) throws IllegalBorrowableException {
        if (!books.isCheckedOut(borrowable))
            throw new IllegalBorrowableException();
        books.borrowableAvailable(borrowable);
    }
}
