package com.tw.pathashala69.biblioteca.models;

import com.tw.pathashala69.biblioteca.exception.BookNotAvailable;
import com.tw.pathashala69.biblioteca.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BooksTest {

    private Book book, book1;
    private Books books;

    @BeforeEach
    void setUp() {
        books = new Books();
        book = mock(Book.class);
        book1 = mock(Book.class);
        books.add(book);
        books.add(book1);

        when(book.title()).thenReturn("Harry Potter");
        when(book1.title()).thenReturn("The Alchemist");
    }

    @Test
    public void shouldReturnFalseIfBookIsNotCheckedOut() {
        assertFalse(books.isCheckedOut(book));
    }

    @Test
    public void shouldReturnTrueIfBookIsCheckedOut() throws BookNotAvailable {
        books.checkout(book);

        assertTrue(books.isCheckedOut(book));
    }

    @Test
    public void shouldThrowBookNotAvailableExceptionIfBookIsAlreadyCheckedOut() throws BookNotAvailable {
        books.checkout(book);

        assertThrows(BookNotAvailable.class, () -> books.checkout(book));
    }

    @Test
    public void shouldUpdateCheckedOutStatusToFalseWhenNewBookIsAdded() {
        Book newBook = mock(Book.class);

        books.add(newBook);

        assertFalse(books.isCheckedOut(book));
    }

    @Test
    public void shouldReturnListOfAvailableBooks() {
        assertThat(books, is(equalTo(books.available())));
    }

    @Test
    public void shouldReturnBookIfSearchedByNameHarryPotter() throws BookNotFoundException {
        Book searchedBook = books.searchByName("Harry Potter");

        assertThat(searchedBook.title(), is(equalTo(book.title())));
    }

    @Test
    public void shouldReturnBookIfSearchedByNameTheAlchemist() throws BookNotFoundException {
        Book searchedBook = books.searchByName("The Alchemist");

        assertThat(searchedBook.title(), is(equalTo(book1.title())));
    }

    @Test
    public void shouldThrowBookNotFoundExceptionIfNoBookFoundWithGivenName() {
        assertThrows(BookNotFoundException.class, () -> books.searchByName("Random Book"));
    }
}
