package com.tw.pathashala69.biblioteca.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class LibraryTest {

    Books books;
    Library library;

    @BeforeEach
    public void setup() {
        books = new Books();
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        library = new Library(books);
    }

    @Test
    public void shouldReturnListOfAllBooksFromLibrary() {
        assertThat(books, is(equalTo(library.books())));
    }

    @Test
    public void shouldCheckoutBook() {

    }
}
