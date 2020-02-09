package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.constants.Message;

import java.io.PrintStream;
import java.util.Objects;

//Job: Represents Book
public class Book {
    private final String name;
    private final String author;
    private final int yearOfPublication;

    public Book(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public void print(PrintStream stream) {
        stream.println(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
//        return String.format("|%-25s|", name) + String.format("%-25s|", author) + String.format("%d|", yearOfPublication);
        return String.format(Message.BOOK_FORMAT, name, author, yearOfPublication);
    }
}
