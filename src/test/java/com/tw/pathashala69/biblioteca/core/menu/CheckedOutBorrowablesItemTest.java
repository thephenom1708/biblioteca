package com.tw.pathashala69.biblioteca.core.menu;

import com.tw.pathashala69.biblioteca.core.auth.User;
import com.tw.pathashala69.biblioteca.core.models.Borrowable;
import com.tw.pathashala69.biblioteca.core.models.Borrowables;
import com.tw.pathashala69.biblioteca.core.models.Library;
import com.tw.pathashala69.biblioteca.core.ui.BorrowableInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CheckedOutBorrowablesItemTest {

    private HashMap<Borrowable, User> borrowableUserMapping;
    private BorrowableInterface<Borrowable> borrowableInterface;
    private CheckedOutBorrowablesItem<Borrowable> checkedOutBorrowablesItem;

    @BeforeEach
    void setUp() {
        borrowableInterface = mock(BorrowableInterface.class);
        Borrowables<Borrowable> borrowables = mock(Borrowables.class);
        Library<Borrowable> library = mock(Library.class);
        borrowableUserMapping = new HashMap<>();

        when(library.borrowables()).thenReturn(borrowables);
        when(borrowables.checkedOut()).thenReturn(borrowableUserMapping);

        checkedOutBorrowablesItem = new CheckedOutBorrowablesItem<>("List of Checked Out Borrowables", "COB",
                borrowableInterface, library);
    }

    @Test
    public void shouldReturnName() {
        assertThat(checkedOutBorrowablesItem.present(), is(equalTo("List of Checked Out Borrowables")));
    }

    @Test
    public void shouldReturnSymbol() {
        assertThat(checkedOutBorrowablesItem.symbol(), is(equalTo("COB")));
    }

    @Test
    public void shouldListCheckedOutBorrowablesAlongWithTheirBorrowerUsers() {
        checkedOutBorrowablesItem.onSelect();

        verify(borrowableInterface, times(1)).listCheckedOutBorrowables(borrowableUserMapping);
    }
}