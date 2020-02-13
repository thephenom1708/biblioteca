package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CheckoutBorrowableItemTest {

    private Book book;
    private Borrowables<Borrowable> borrowables;
    private Library<Borrowable> library;
    private UserInterface<Borrowable> userInterface;
    private CheckoutBorrowableItem<Borrowable> checkoutBorrowableItem;

    @BeforeEach
    void setUp() {
        book = mock(Book.class);
        borrowables = mock(Borrowables.class);
        library = mock(Library.class);
        userInterface = mock(UserInterface.class);

        when(borrowables.add(book)).thenReturn(true);
        when(userInterface.promptForCheckoutBorrowable()).thenReturn("Harry Potter");
        when(library.borrowables()).thenReturn(borrowables);

        checkoutBorrowableItem = new CheckoutBorrowableItem<>("Checkout Book", "CB",
                userInterface, library);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void shouldCheckoutSelectedBook() throws BorrowableNotAvailableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);

        checkoutBorrowableItem.onSelect();

        verify(library, times(1)).checkout(book);
    }

    @Test
    public void shouldPerformOnCheckoutSuccessfulWhenCheckoutIsSuccessful() throws BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);

        checkoutBorrowableItem.onSelect();

        verify(userInterface, times(1)).onCheckoutBorrowableSuccess();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsNotFound() throws BorrowableNotFoundException {
        //noinspection unchecked
        when(borrowables.searchByName("Harry Potter")).thenThrow(BorrowableNotFoundException.class);

        checkoutBorrowableItem.onSelect();

        verify(userInterface, times(1)).onCheckoutBorrowableUnsuccessful();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsAlreadyCheckedOut() throws BorrowableNotAvailableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);
        doThrow(BorrowableNotAvailableException.class).when(library).checkout(book);

        checkoutBorrowableItem.onSelect();

        verify(userInterface, times(1)).onCheckoutBorrowableUnsuccessful();
    }

}
