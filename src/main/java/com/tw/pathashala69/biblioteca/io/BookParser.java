package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.Book;

public class BookParser {

    public Book parse(String name) {
        return new Book(name);
    }
}
