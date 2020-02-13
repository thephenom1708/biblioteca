package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ReturnBorrowableItemTest {

    private Book book;
    private Borrowables borrowables;
    private Library library;
    private UserInterface userInterface;
    private ReturnBorrowableItem returnBorrowableItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        borrowables = mock(Borrowables.class);
        library = mock(Library.class);
        userInterface = mock(Biblioteca.class);

        when(borrowables.add(book)).thenReturn(true);
        when(userInterface.promptForReturnBook()).thenReturn("Harry Potter");
        when(library.books()).thenReturn(borrowables);

        returnBorrowableItem = new ReturnBorrowableItem(userInterface, library);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnSelectedBook() throws IllegalBorrowableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);

        returnBorrowableItem.onSelect();

        verify(library, times(1)).returnBorrowable(book);
    }

    @Test
    public void shouldPerformOnReturnSuccessfulWhenReturnIsSuccessful() throws BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);

        returnBorrowableItem.onSelect();

        verify(userInterface, times(1)).onReturnBookSuccess();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookDoesNotBelongToLibrary() throws BorrowableNotFoundException {
        //noinspection unchecked
        when(borrowables.searchByName("Harry Potter")).thenThrow(BorrowableNotFoundException.class);

        returnBorrowableItem.onSelect();

        verify(userInterface, times(1)).onReturnBookUnsuccessful();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookWasNotCheckedOutBefore() throws IllegalBorrowableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);
        doThrow(IllegalBorrowableException.class).when(library).returnBorrowable(book);

        returnBorrowableItem.onSelect();

        verify(userInterface, times(1)).onReturnBookUnsuccessful();
    }
}