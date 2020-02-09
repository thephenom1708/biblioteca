package com.tw.pathashala69.biblioteca.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Job: Represents collection of books
public class Books extends ArrayList<Book> {

    private HashMap<Book, Boolean> checkoutStatus = new HashMap<>();

    public Books() {}

    public Books(List<Book> books) {
        books.forEach(book -> {
            this.add(book);
            checkoutStatus.put(book, false);
        });
    }

    @Override
    public boolean add(Book book) {
        boolean added = super.add(book);
        if(added)
            checkoutStatus.put(book, false);
        return added;
    }

    public void checkout(Book book) {
        checkoutStatus.put(book, true);
    }

    public boolean isCheckedOut(Book book) {
        return checkoutStatus.get(book);
    }

    public Books available() {
        Books availableBooks = new Books();
        this.forEach(book -> {
            if(!isCheckedOut(book))
                availableBooks.add(book);
        });
        return availableBooks;
    }
}
