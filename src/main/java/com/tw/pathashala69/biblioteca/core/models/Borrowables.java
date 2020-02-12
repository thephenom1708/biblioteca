package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Job: Represents collection of books
public class Borrowables extends ArrayList<Borrowable> {
    private HashMap<Borrowable, Boolean> checkoutStatus = new HashMap<>();

    public Borrowables() {}

    public Borrowables(List<? extends Borrowable> borrowables) {
        borrowables.forEach(borrowable -> {
            this.add(borrowable);
            checkoutStatus.put(borrowable, false);
        });
    }

    public void checkout(Borrowable borrowable) throws BorrowableNotAvailableException {
        if (isCheckedOut(borrowable))
            throw new BorrowableNotAvailableException();
        checkoutStatus.put(borrowable, true);
    }

    public void returnBorrowable(Borrowable borrowable) throws IllegalBorrowableException {
        if (!isCheckedOut(borrowable))
            throw new IllegalBorrowableException();
        checkoutStatus.put(borrowable, false);
    }

    public Borrowables available() {
        Borrowables availableBorrowables = new Borrowables();
        this.forEach(borrowable -> {
            if (!isCheckedOut(borrowable))
                availableBorrowables.add(borrowable);
        });
        return availableBorrowables;
    }

    public Borrowable searchByName(String title) throws BorrowableNotFoundException {
        for (Borrowable borrowable : this) {
            if (borrowable.title().equals(title))
                return borrowable;
        }
        throw new BorrowableNotFoundException();
    }

    boolean isCheckedOut(Borrowable borrowable) {
        return checkoutStatus.get(borrowable);
    }

    @Override
    public boolean add(Borrowable borrowable) {
        boolean added = super.add(borrowable);
        if (added)
            checkoutStatus.put(borrowable, false);
        return added;
    }
}
