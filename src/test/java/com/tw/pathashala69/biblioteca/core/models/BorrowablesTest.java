package com.tw.pathashala69.biblioteca.core.models;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BorrowablesTest {

    private Borrowable book, book1;
    private Borrowables borrowables;
    private User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        borrowables = new Borrowables();
        book = mock(Book.class);
        book1 = mock(Book.class);
        borrowables.add(book);
        borrowables.add(book1);

        when(book.title()).thenReturn("Harry Potter");
        when(book1.title()).thenReturn("The Alchemist");
    }

    @Test
    public void shouldSetBorrowableAsCheckedOut() {
        borrowables.borrowableCheckOut(book, user);

        assertTrue(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldSetBorrowableAsAvailable() {
        borrowables.borrowableAvailable(book);

        assertFalse(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldAddAnotherBorrowablesToThisBorrowables() {
        Borrowables anotherBorrowables = new Borrowables(List.of(book, book1));
        Borrowables expectedBorrowables = new Borrowables(List.of(book, book1, book, book1));
        borrowables.add(anotherBorrowables);

        assertThat(borrowables, is(equalTo(expectedBorrowables)));
    }

    @Test
    public void shouldReturnFalseIfBorrowableIsNotCheckedOut() {
        assertFalse(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldReturnTrueOnIsCheckedOutIfBorrowableIsCheckedOut() {
        borrowables.borrowableCheckOut(book, user);

        assertTrue(borrowables.isCheckedOut(book));
    }

    @Test
    public void shouldReturnUserWhoCheckedOutBorrowable() {
        borrowables.borrowableCheckOut(book, user);

        assertThat(borrowables.checkedOutUser(book), is(equalTo(user)));
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
    public void shouldReturnCheckedOutBorrowablesAndUserMapping() {
        borrowables.borrowableCheckOut(book, user);
        HashMap<Borrowable, User> expectedMapping = new HashMap<>(){{
            put(book, user);
        }};

        assertThat(borrowables.checkedOut(), is(equalTo(expectedMapping)));
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
    public void shouldReturnFalseOnIsCheckedOutIfBorrowableIsReturned() {
        borrowables.borrowableAvailable(book);

        assertFalse(borrowables.isCheckedOut(book));
    }
}
