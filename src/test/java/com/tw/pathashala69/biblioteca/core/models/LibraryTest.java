package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
class LibraryTest {

    private User user;
    private Book book;
    private Borrowables borrowables;
    private Library library;

    @BeforeEach
    public void setup() {
        user = mock(User.class);
        book = mock(Book.class);
        borrowables = mock(Borrowables.class);
        borrowables.add(book);

        library = new Library(borrowables);
    }

    @Test
    public void shouldReturnListOfAllBorrowablesFromLibrary() {
        assertThat(borrowables, is(equalTo(library.borrowables())));
    }

    @Test
    public void shouldCheckoutBook() throws BorrowableNotAvailableException {
        when(borrowables.isCheckedOut(book)).thenReturn(false);
        library.checkout(book, user);

        verify(borrowables, times(1)).borrowableCheckOut(book, user);
    }

    @Test
    public void shouldThrowBorrowableNotAvailableExceptionIfBorrowableIsAlreadyCheckedOut() {
        when(borrowables.isCheckedOut(book)).thenReturn(true);

        assertThrows(BorrowableNotAvailableException.class, () -> library.checkout(book, user));
    }


    @Test
    public void shouldReturnBook() throws IllegalBorrowableException {
        when(borrowables.isCheckedOut(book)).thenReturn(true);
        library.returnBorrowable(book);

        verify(borrowables, times(1)).borrowableAvailable(book);
    }

    @Test
    public void shouldThrowIllegalBorrowableExceptionIfBorrowableWasNotCheckedOutBefore() {
        when(borrowables.isCheckedOut(book)).thenReturn(false);

        assertThrows(IllegalBorrowableException.class, () -> library.returnBorrowable(book));
    }
}
