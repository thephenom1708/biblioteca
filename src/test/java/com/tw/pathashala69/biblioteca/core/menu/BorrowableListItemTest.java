package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class BorrowableListItemTest {

    private Borrowables<Borrowable> borrowables;
    private UserInterface<Borrowable> userInterface;
    private BorrowableListItem<Borrowable> borrowableListItem;


    @BeforeEach
    void setUp() {
        userInterface = mock(UserInterface.class);
        borrowables = mock(Borrowables.class);
        Library<Borrowable> library = mock(Library.class);

        when(library.borrowables()).thenReturn(borrowables);
        when(borrowables.available()).thenReturn(borrowables);

        borrowableListItem = new BorrowableListItem<>("List of Movies", "M", userInterface, library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(borrowableListItem.present(), is(equalTo("List of Movies")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(borrowableListItem.symbol(), is(equalTo("M")));
    }

    @Test
    public void shouldPrintAvailableBooksOnSelection() {
        borrowableListItem.onSelect();

        verify(userInterface, times(1)).listBorrowables(borrowables.available());
    }
}