package com.tw.pathashala69.biblioteca;

import java.util.List;

//Job: Represents Library
public class Library {
    private final List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> books() {
        return books;
    }
}
