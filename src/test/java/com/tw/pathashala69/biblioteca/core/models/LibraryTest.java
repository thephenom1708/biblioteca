package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class LibraryTest {

    private Book book;
    private Books books;
    private Library library;

    @BeforeEach
    public void setup() {
        book = mock(Book.class);
        books = mock(Books.class);
        books.add(book);

        library = new Library(books);
    }

    @Test
    public void shouldReturnListOfAllBooksFromLibrary() {
        assertThat(books, is(equalTo(library.books())));
    }

    @Test
    public void shouldCheckoutBook() throws BookNotAvailable {
        library.checkout(book);

        verify(books, times(1)).checkout(book);
    }

    @Test
    public void shouldReturnBook() throws IllegalBookException {
        library.returnBook(book);

        verify(books, times(1)).returnBook(book);
    }
}
