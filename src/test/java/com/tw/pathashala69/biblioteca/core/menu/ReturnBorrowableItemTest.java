package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.exception.IllegalBorrowableException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class ReturnBorrowableItemTest {

    private Book book;
    private Borrowables<Borrowable> borrowables;
    private Library<Borrowable> library;
    private UserInterface<Borrowable> userInterface;
    private ReturnBorrowableItem<Borrowable> returnBorrowableItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        borrowables = mock(Borrowables.class);
        library = mock(Library.class);
        userInterface = mock(UserInterface.class);

        when(borrowables.add(book)).thenReturn(true);
        when(userInterface.promptForReturnBorrowable()).thenReturn("Harry Potter");
        when(library.borrowables()).thenReturn(borrowables);

        returnBorrowableItem = new ReturnBorrowableItem<>("Return Movie", "RM", userInterface, library);
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

        verify(userInterface, times(1)).onReturnBorrowableSuccess();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookDoesNotBelongToLibrary() throws BorrowableNotFoundException {
        //noinspection unchecked
        when(borrowables.searchByName("Harry Potter")).thenThrow(BorrowableNotFoundException.class);

        returnBorrowableItem.onSelect();

        verify(userInterface, times(1)).onReturnBorrowableUnsuccessful();
    }

    @Test
    public void shouldPerformOnReturnUnsuccessfulWhenBookWasNotCheckedOutBefore() throws IllegalBorrowableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);
        doThrow(IllegalBorrowableException.class).when(library).returnBorrowable(book);

        returnBorrowableItem.onSelect();

        verify(userInterface, times(1)).onReturnBorrowableUnsuccessful();
    }
}