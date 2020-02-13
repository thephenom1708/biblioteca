package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;

import java.util.HashMap;

//Job: Represents Library
public class Library {
    private final Borrowables books;
    private final Borrowables movies;
    private HashMap<Borrowable, Boolean> checkoutStatus = new HashMap<>();

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
        books.checkout(borrowable);
    }

    public void returnBorrowable(Borrowable borrowable) throws IllegalBorrowableException {
        books.returnBorrowable(borrowable);
    }
}
