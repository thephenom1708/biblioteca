package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.constants.Message;

import java.io.PrintStream;
import java.util.Objects;

//Job: Represents Book
public class Book {
    private final String title;
    private final String author;
    private final int yearOfPublication;

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String title() {
        return title;
    }

    public void print(PrintStream stream) {
        stream.println(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return String.format(Message.BOOK_PRINT_FORMAT, title, author, yearOfPublication);
    }
}
