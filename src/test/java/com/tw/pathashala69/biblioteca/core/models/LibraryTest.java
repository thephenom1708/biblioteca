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
    private Borrowables borrowables;
    private Library library;

    @BeforeEach
    public void setup() {
        book = mock(Book.class);
        borrowables = mock(Borrowables.class);
        borrowables.add(book);

        library = new Library(borrowables);
    }

    @Test
    public void shouldReturnListOfAllBooksFromLibrary() {
        assertThat(borrowables, is(equalTo(library.books())));
    }

    @Test
    public void shouldCheckoutBook() throws BorrowableNotAvailableException {
        library.checkout(book);

        verify(borrowables, times(1)).checkout(book);
    }

    @Test
    public void shouldReturnBook() throws IllegalBorrowableException {
        library.returnBorrowable(book);

        verify(borrowables, times(1)).returnBorrowable(book);
    }
}
