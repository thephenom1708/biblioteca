package com.tw.pathashala69.biblioteca.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BooksTest {

    private Book book;
    private Books books;

    @BeforeEach
    void setUp() {
        books = new Books(List.of(mock(Book.class), mock(Book.class), mock(Book.class)));
        book = mock(Book.class);
        books.add(book);
    }

    @Test
    public void shouldReturnFalseIfBookIsNotCheckedOut() {
        assertFalse(books.isCheckedOut(book));
    }

    @Test
    public void shouldReturnTrueIfBookIsCheckedOut() {
        books.checkout(book);

        assertTrue(books.isCheckedOut(book));
    }
}