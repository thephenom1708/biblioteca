package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.Biblioteca;
import com.tw.pathashala69.biblioteca.UserInterface;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class BorrowableListItemTest {

    private Borrowables borrowables;
    private UserInterface userInterface;
    private BorrowableListItem borrowableListItem;

    @BeforeEach
    void setUp() {
        userInterface = mock(Biblioteca.class);
        borrowables = mock(Borrowables.class);
        Library library = mock(Library.class);

        when(library.books()).thenReturn(borrowables);
        when(borrowables.available()).thenReturn(borrowables);

        borrowableListItem = new BorrowableListItem(userInterface, library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(borrowableListItem.present(), is(equalTo("List of books")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(borrowableListItem.symbol(), is(equalTo("B")));
    }

    @Test
    public void shouldPrintAvailableBooksOnSelection() {
        borrowableListItem.onSelect();

        verify(userInterface, times(1)).printBorrowable(borrowables.available());
    }
}
