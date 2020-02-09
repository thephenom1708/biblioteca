package com.tw.pathashala69.biblioteca.models;

//Job: Represents Library
public class Library {
    private final Books books;

    public Library(Books books) {
        this.books = books;
    }

    public Books books() {
        return books;
    }
}
