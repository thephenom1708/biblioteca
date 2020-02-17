package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Job: Represents collection of books
public class Borrowables<T extends Borrowable> extends ArrayList<Borrowable> {
    private HashMap<Borrowable, Boolean> checkoutStatus = new HashMap<>();
    private HashMap<Borrowable, User> userBorrowableMapping = new HashMap<>();

    public Borrowables() {}

    public Borrowables(List<T> borrowables) {
        borrowables.forEach(borrowable -> {
            this.add(borrowable);
            checkoutStatus.put(borrowable, false);
        });
    }

    public void borrowableCheckOut(Borrowable borrowable, User user) {
        checkoutStatus.put(borrowable, true);
        userBorrowableMapping.put(borrowable, user);
    }

    public void borrowableAvailable(Borrowable borrowable) {
        checkoutStatus.put(borrowable, false);
    }

    public Borrowables<T> available() {
        Borrowables<T> availableBorrowables = new Borrowables<>();
        this.forEach(borrowable -> {
            if (!isCheckedOut(borrowable))
                availableBorrowables.add(borrowable);
        });
        return availableBorrowables;
    }

    public HashMap<Borrowable, User> checkedOut() {
        return userBorrowableMapping;
    }

    public Borrowable searchByName(String title) throws BorrowableNotFoundException {
        for (Borrowable borrowable : this) {
            if (borrowable.title().equals(title))
                return borrowable;
        }
        throw new BorrowableNotFoundException();
    }

    public User checkedOutUser(Borrowable borrowable) {
        return userBorrowableMapping.get(borrowable);
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

    public void add(Borrowables<Borrowable> borrowables) {
        this.addAll(borrowables);
    }
}
