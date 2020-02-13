package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class LibraryTest {

    private Book book;
    private Borrowables books, movies;
    private Library library;

    @BeforeEach
    public void setup() {
        book = mock(Book.class);
        books = mock(Borrowables.class);
        movies = mock(Borrowables.class);
        books.add(book);

        library = new Library(books, movies);
    }

    @Test
    public void shouldReturnListOfAllBooksFromLibrary() {
        assertThat(books, is(equalTo(library.books())));
    }

    @Test
    public void shouldReturnListOfAllMoviesFromLibrary() {
        assertThat(movies, is(equalTo(library.movies())));
    }

    @Test
    public void shouldCheckoutBook() throws BorrowableNotAvailableException {
        library.checkout(book);

        verify(books, times(1)).checkout(book);
    }

    @Test
    public void shouldReturnBook() throws IllegalBorrowableException {
        library.returnBorrowable(book);

        verify(books, times(1)).returnBorrowable(book);
    }
}
