package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;

//Job: Represents Library
public class Library<T extends Borrowable> {
    private final Borrowables<T> borrowables;

    public Library(Borrowables<T> borrowables) {
        this.borrowables = borrowables;
    }

    public Borrowables<T> borrowables() {
        return borrowables;
    }

    public void checkout(Borrowable borrowable) throws BorrowableNotAvailableException {
        if (borrowables.isCheckedOut(borrowable))
            throw new BorrowableNotAvailableException();
        borrowables.borrowableCheckedOut(borrowable);
    }

    public void returnBorrowable(Borrowable borrowable) throws IllegalBorrowableException {
        if (!borrowables.isCheckedOut(borrowable))
            throw new IllegalBorrowableException();
        borrowables.borrowableAvailable(borrowable);
    }
}
