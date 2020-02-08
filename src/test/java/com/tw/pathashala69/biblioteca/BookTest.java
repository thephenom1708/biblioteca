package com.tw.pathashala69.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void setUp() {
        book = new Book("Harry Potter", "J. K. Rowling", 2019);
        outStream = new ByteArrayOutputStream();
    }

    @Test
    public void shouldPrintBookDetails() {
        System.setOut(new PrintStream(outStream));

        book.print(System.out);
        System.setOut(new PrintStream(System.out));

        assertTrue(new String(outStream.toByteArray()).contains(book.toString()));
    }
}
