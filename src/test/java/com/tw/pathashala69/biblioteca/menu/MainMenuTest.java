package com.tw.pathashala69.biblioteca.menu;

import com.tw.pathashala69.biblioteca.core.exception.InvalidMenuOptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MainMenuTest {

    private MainMenu mainMenu;
    private List<MenuItem> menuItems;
    private BorrowableListItem testBorrowableListItem;
    private CheckoutBorrowableItem testCheckoutBorrowableItem;
    private ReturnBorrowableItem testReturnBorrowableItem;
    private QuitItem testQuitItem;

    @BeforeEach
    void setUp() {
        testBorrowableListItem = mock(BorrowableListItem.class);
        testCheckoutBorrowableItem = mock(CheckoutBorrowableItem.class);
        testReturnBorrowableItem = mock(ReturnBorrowableItem.class);
        testQuitItem = mock(QuitItem.class);

        menuItems = List.of(testBorrowableListItem, testCheckoutBorrowableItem, testReturnBorrowableItem, testQuitItem);

        when(testBorrowableListItem.symbol()).thenReturn("B");
        when(testBorrowableListItem.present()).thenReturn("List of books");
        when(testCheckoutBorrowableItem.present()).thenReturn("Checkout Book");
        when(testCheckoutBorrowableItem.symbol()).thenReturn("CB");
        when(testReturnBorrowableItem.present()).thenReturn("Return Book");
        when(testReturnBorrowableItem.symbol()).thenReturn("RB");
        when(testQuitItem.symbol()).thenReturn("Q");
        when(testQuitItem.present()).thenReturn("Quit");

        mainMenu = new MainMenu(menuItems);
    }

    @Test
    public void shouldReturnListOfAllMenuItems() {
        assertThat(mainMenu.menuItems(), is(equalTo(menuItems)));
    }

    @Test
    @DisplayName("View list of books when input string is B")
    public void shouldExecuteBookListItemWhenInputIsB() throws InvalidMenuOptionException {
        mainMenu.execute("B");

        verify(testBorrowableListItem, times(1)).onSelect();
    }

    @Test
    @DisplayName("Checkout book when input string is CB")
    public void shouldExecuteCheckoutBookItemWhenInputIsCB() throws InvalidMenuOptionException {
        mainMenu.execute("CB");

        verify(testCheckoutBorrowableItem, times(1)).onSelect();
    }

    @Test
    @DisplayName("Return book when input string is RB")
    public void shouldExecuteReturnBookItemWhenInputIsRB() throws InvalidMenuOptionException {
        mainMenu.execute("RB");

        verify(testReturnBorrowableItem, times(1)).onSelect();
    }

    @Test
    public void shouldThrowExceptionIfInvalidInputIsGiven() {
        assertThrows(InvalidMenuOptionException.class, () -> mainMenu.execute("A"));
    }

    @Test
    @DisplayName("Quit application when input string is Q")
    public void shouldExecuteQuitItemWhenInputIsQ() throws InvalidMenuOptionException {
        mainMenu.execute("Q");

        verify(testQuitItem, times(1)).onSelect();
    }
}
