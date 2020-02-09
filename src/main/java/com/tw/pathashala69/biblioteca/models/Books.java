package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;

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

    public void checkout(Book book) throws BookNotAvailable {
        if(isCheckedOut(book))
            throw new BookNotAvailable();
        checkoutStatus.put(book, true);
    }

    boolean isCheckedOut(Book book) {
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

    public Book searchByName(String name) throws BookNotFoundException {
        for(Book book : this) {
            if(book.title().equals(name))
                return book;
        }
        throw new BookNotFoundException();
    }
}
