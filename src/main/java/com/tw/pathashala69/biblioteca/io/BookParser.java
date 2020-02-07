package com.tw.pathashala69.biblioteca.io;

import com.tw.pathashala69.biblioteca.Book;

public class BookParser {

    public Book parse(String[] bookParameters) {
        return new Book(bookParameters[0]);
    }

    public String[] parseParameters(String line) {
        return line.split(",");
    }
}
