package com.tw.pathashala69.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class LibraryTest {

    List<Book> books;
    Library library;

    @BeforeEach
    public void setup() {
        books = List.of(mock(Book.class), mock(Book.class), mock(Book.class));
        library = new Library(books);
    }

    @Test
    public void shouldReturnListOfAllBooksFromLibrary() {
        assertThat(books, is(equalTo(library.books())));
    }
}
