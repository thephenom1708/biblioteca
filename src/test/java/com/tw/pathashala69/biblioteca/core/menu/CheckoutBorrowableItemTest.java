package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.Session;
import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.auth.UserAuthentication;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotAvailableException;
import com.tw.pathashala69.biblioteca.core.exception.BorrowableNotFoundException;
import com.tw.pathashala69.biblioteca.core.models.Book;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class CheckoutBorrowableItemTest {

    private User user;
    private Book book;
    private Session session;
    private Borrowables<Borrowable> borrowables;
    private Library<Borrowable> library;
    private BorrowableInterface<Borrowable> borrowableInterface;
    private CheckoutBorrowableItem<Borrowable> checkoutBorrowableItem;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        book = mock(Book.class);
        session = mock(Session.class);
        borrowables = mock(Borrowables.class);
        library = mock(Library.class);
        borrowableInterface = mock(BorrowableInterface.class);
        UserAuthentication userAuth = mock(UserAuthentication.class);

        when(borrowables.add(book)).thenReturn(true);
        when(borrowableInterface.promptForCheckoutBorrowable()).thenReturn("Harry Potter");
        when(library.borrowables()).thenReturn(borrowables);

        checkoutBorrowableItem = new CheckoutBorrowableItem<>("Checkout Book", "CB",
                borrowableInterface, library, userAuth);

        when(userAuth.activeSession()).thenReturn(session);
        when(session.user()).thenReturn(user);
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

        verify(library, times(1)).checkout(book, user);
    }

    @Test
    public void shouldPerformOnCheckoutSuccessfulWhenCheckoutIsSuccessful() throws BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);

        checkoutBorrowableItem.onSelect();

        verify(borrowableInterface, times(1)).onCheckoutBorrowableSuccess();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsNotFound() throws BorrowableNotFoundException {
        when(borrowables.searchByName("Harry Potter")).thenThrow(BorrowableNotFoundException.class);

        checkoutBorrowableItem.onSelect();

        verify(borrowableInterface, times(1)).onCheckoutBorrowableUnsuccessful();
    }

    @Test
    public void shouldPerformOnCheckoutUnsuccessfulWhenBookIsAlreadyCheckedOut() throws BorrowableNotAvailableException, BorrowableNotFoundException {
        borrowables.add(book);
        when(borrowables.searchByName("Harry Potter")).thenReturn(book);
        doThrow(BorrowableNotAvailableException.class).when(library).checkout(book, user);

        checkoutBorrowableItem.onSelect();

        verify(borrowableInterface, times(1)).onCheckoutBorrowableUnsuccessful();
    }
}
