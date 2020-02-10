package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
    public void shouldEquateTwoBooksWithSameTitle() {
        Book book = new Book("Book1", "Some author", 2000);
        Book anotherBook = new Book("Book1", "Some author", 2000);

        assertThat(book, is(equalTo(anotherBook)));
    }

    @Test
    public void shouldNotEquateTwoBooksWithDifferentTitle() {
        Book book = new Book("Book1", "Some author", 2000);
        Book anotherBook = new Book("Book2", "Some author", 2000);

        assertThat(book, is(not(equalTo(anotherBook))));
    }

    @Test
    public void shouldReturnTitleOfBook() {
        assertThat(book.title(), is(equalTo("Harry Potter")));
    }

    @Test
    public void shouldPrintBookDetails() {
        System.setOut(new PrintStream(outStream));

        book.print(System.out);
        System.setOut(new PrintStream(System.out));

        assertTrue(new String(outStream.toByteArray()).contains(book.toString()));
    }
}
