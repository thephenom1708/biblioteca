package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BorrowablesTest {

    private Book book, book1;
    private Borrowables borrowables;

    @BeforeEach
    void setUp() {
        borrowables = new Borrowables();
        book = mock(Book.class);
        book1 = mock(Book.class);
        borrowables.add(book);
        borrowables.add(book1);

        when(book.title()).thenReturn("Harry Potter");
        when(book1.title()).thenReturn("The Alchemist");
    }

    @Test
    public void shouldReturnFalseIfBorrowableIsNotCheckedOut() {
        assertFalse(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldReturnTrueOnIsCheckedOutIfBorrowableIsCheckedOut() throws BorrowableNotAvailableException {
        borrowables.checkout(book);

        assertTrue(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldThrowBorrowableNotAvailableExceptionIfBorrowableIsAlreadyCheckedOut() throws BorrowableNotAvailableException {
        borrowables.checkout(book);

        assertThrows(BorrowableNotAvailableException.class, () -> borrowables.checkout(book));
    }

    @Test
    public void shouldUpdateCheckedOutStatusToFalseWhenNewBorrowableIsAdded() {
        Book newBook = mock(Book.class);

        borrowables.add(newBook);

        assertFalse(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldReturnListOfAvailableBorrowables() {
        assertThat(borrowables, is(equalTo(borrowables.available())));
    }

    @Test
    public void shouldReturnBorrowableIfSearchedByNameHarryPotter() throws BorrowableNotFoundException {
        Borrowable searchedBorrowable = borrowables.searchByName("Harry Potter");

        assertThat(searchedBorrowable.title(), is(equalTo(searchedBorrowable.title())));
    }

    @Test
    public void shouldReturnBorrowableIfSearchedByNameTheAlchemist() throws BorrowableNotFoundException {
        Borrowable searchedBook = borrowables.searchByName("The Alchemist");

        assertThat(searchedBook.title(), is(equalTo(book1.title())));
    }

    @Test
    public void shouldThrowBorrowableNotFoundExceptionIfNoBorrowableFoundWithGivenName() {
        assertThrows(BorrowableNotFoundException.class, () -> borrowables.searchByName("Random Book"));
    }

    @Test
    public void shouldReturnFalseOnIsCheckedOutIfBorrowableIsReturned() throws BorrowableNotAvailableException, IllegalBorrowableException {
        borrowables.checkout(book);

        borrowables.returnBorrowable(book);

        assertFalse(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldThrowIllegalBorrowableExceptionIfBorrowableWasNotCheckedOutBefore() {
        assertThrows(IllegalBorrowableException.class, () -> borrowables.returnBorrowable(book));
    }
}
